package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.topic.CreateTopicRequest;
import dev.dani.strong.newsfeed.model.dto.request.topic.UpdateTopicRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.entity.Topic;

import java.util.List;

public interface TopicService {
    CreatedResponse create(CreateTopicRequest createRequest);

    List<Topic> fetchAll();

    Topic fetchById(Long id);

    Topic updateById(Long id, UpdateTopicRequest updateRequest);

    DeletedResponse deleteById(Long id);
}
