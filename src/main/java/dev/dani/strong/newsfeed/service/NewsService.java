package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.news.CreateNewsRequest;
import dev.dani.strong.newsfeed.model.dto.request.news.UpdateNewsRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.dto.response.news.SingleNews;
import dev.dani.strong.newsfeed.model.entity.News;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    CreatedResponse create(CreateNewsRequest createRequest, Authentication authentication);

    SingleNews fetchById(Long id);

    List<SingleNews> fetchAll(Optional<Integer> page, Optional<Integer> size, Optional<String> sort);

    List<SingleNews> fetchBySourceId(Long id, Optional<Integer> page, Optional<Integer> size, Optional<String> sort);

    List<SingleNews> fetchByTopicId(Long id, Optional<Integer> page, Optional<Integer> size, Optional<String> sort);

    SingleNews updateById(Long id, UpdateNewsRequest updateRequest);

    DeletedResponse deleteById(Long id);
}
