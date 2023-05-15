package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceNotFoundException;
import dev.dani.strong.newsfeed.model.dto.request.Validatable;
import dev.dani.strong.newsfeed.model.dto.request.newsSource.CreateNewsSourceRequest;
import dev.dani.strong.newsfeed.model.dto.request.newsSource.UpdateNewsSourceRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.entity.NewsSource;
import dev.dani.strong.newsfeed.repository.NewsSourceRepository;
import dev.dani.strong.newsfeed.service.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsSourceServiceImpl implements NewsSourceService, Validatable<CreateNewsSourceRequest> {

    private final NewsSourceRepository newsSourceRepository;

    @Override
    public CreatedResponse create(CreateNewsSourceRequest createRequest) {

        checkExistenceForCreation(createRequest);
        String sourceName = formatSourceName(createRequest.sourceName());

        var entity = NewsSource.builder()
                .name(createRequest.name().trim())
                .sourceName(sourceName)
                .description(createRequest.description().trim())
                .url(createRequest.url().trim())
                .registeredAt(LocalDate.now())
                .build();

        newsSourceRepository.save(entity);

        return CreatedResponse.builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now())
                .data(entity)
                .build();
    }

    @Override
    public NewsSource fetchById(Long id) {
        return newsSourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News source with id: [" + id + "] not found."));
    }

    @Override
    public NewsSource fetchBySourceName(String sourceName) {
        String source = sourceName.contains("@") ? sourceName.trim() : "@" + sourceName.trim();

        return newsSourceRepository.findBySourceNameEqualsIgnoreCase(source)
                .orElseThrow(() -> new ResourceNotFoundException("News source: [" + sourceName + "] not found."));
    }

    @Override
    public List<NewsSource> fetchAll() {
        return newsSourceRepository.findAll();
    }

    @Override
    public NewsSource updateById(Long id, UpdateNewsSourceRequest updateRequest) {
        var newsSourceFromDatabase = fetchById(id);

        if (Objects.nonNull(updateRequest.name()) && !updateRequest.name().equalsIgnoreCase(EMPTY_STRING)) {
            newsSourceFromDatabase.setName(updateRequest.name());
        }
        if (Objects.nonNull(updateRequest.sourceName()) && !updateRequest.sourceName().equalsIgnoreCase(EMPTY_STRING)) {
            String sourceName = formatSourceName(updateRequest.sourceName());
            newsSourceFromDatabase.setSourceName(sourceName);
        }
        if (Objects.nonNull(updateRequest.url()) && !updateRequest.url().equalsIgnoreCase(EMPTY_STRING)) {
            newsSourceFromDatabase.setUrl(updateRequest.url());
        }
        if (Objects.nonNull(updateRequest.description()) && !updateRequest.description().equalsIgnoreCase(EMPTY_STRING)) {
            newsSourceFromDatabase.setDescription(updateRequest.description());
        }

        return newsSourceRepository.save(newsSourceFromDatabase);

    }

    @Override
    public DeletedResponse deleteById(Long id) {

        NewsSource existingNewsSource = fetchById(id);
        newsSourceRepository.delete(existingNewsSource);

        return DeletedResponse.builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(Instant.now())
                .deleted(true)
                .data(existingNewsSource)
                .build();
    }


    @Override
    public void checkExistenceForCreation(CreateNewsSourceRequest request) throws ResourceAlreadyExistException {

        var name = request.name().trim();
        var sourceName = request.sourceName().trim();

        if (newsSourceRepository.existsByNameEqualsIgnoreCase(name)) {
            throw new ResourceAlreadyExistException("News source with name: [" + name + "] already exist.");
        }
        if (newsSourceRepository.existsBySourceNameEqualsIgnoreCase(sourceName)) {
            throw new ResourceAlreadyExistException("News source: [" + sourceName + "] already exist.");
        }
    }

    private String formatSourceName(String sourceName) {
        return sourceName.contains("@") ?
                sourceName.trim().toLowerCase() :
                "@" + sourceName.trim().toLowerCase();
    }
}
