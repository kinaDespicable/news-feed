package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceNotFoundException;
import dev.dani.strong.newsfeed.model.dto.request.Validatable;
import dev.dani.strong.newsfeed.model.dto.request.topic.CreateTopicRequest;
import dev.dani.strong.newsfeed.model.dto.request.topic.UpdateTopicRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.entity.Topic;
import dev.dani.strong.newsfeed.repository.TopicRepository;
import dev.dani.strong.newsfeed.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService, Validatable<CreateTopicRequest> {

    private final TopicRepository topicRepository;

    @Override
    public CreatedResponse create(CreateTopicRequest createRequest) {

        checkExistenceForCreation(createRequest);

        var entity = Topic.builder()
                .topic(createRequest.topic().trim())
                .description(createRequest.description().trim())
                .build();

        topicRepository.save(entity);

        return CreatedResponse.builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now())
                .data(entity)
                .build();

    }

    @Override
    public List<Topic> fetchAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic fetchById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic with id: [" + id + "] not found."));
    }

    @Override
    public Topic fetchByTopicName(String topicName){
        return topicRepository.findTopicByTopicEqualsIgnoreCase(topicName)
                .orElseThrow(() -> new ResourceNotFoundException("Topic: [" + topicName + "] not found."));
    }

    @Override
    public Topic updateById(Long id, UpdateTopicRequest updateRequest) {

        var existingTopic = fetchById(id);

        if (Objects.nonNull(updateRequest.topic()) && !updateRequest.topic().equalsIgnoreCase(EMPTY_STRING)) {
            existingTopic.setTopic(updateRequest.topic());
        }
        if (Objects.nonNull(updateRequest.description()) && !updateRequest.description().equalsIgnoreCase(EMPTY_STRING)) {
            existingTopic.setDescription(updateRequest.description());
        }
        return topicRepository.save(existingTopic);
    }

    @Override
    public DeletedResponse deleteById(Long id) {

        var existingTopic = fetchById(id);
        topicRepository.delete(existingTopic);

        return DeletedResponse.builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(Instant.now())
                .deleted(true)
                .data(existingTopic)
                .build();
    }


    @Override
    public void checkExistenceForCreation(CreateTopicRequest request) throws ResourceAlreadyExistException {
        String topic = request.topic().trim();
        if (topicRepository.existsByTopicIgnoreCase(topic)) {
            throw new ResourceAlreadyExistException("Topic [" + topic + "] already exist.");
        }
    }
}
