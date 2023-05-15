package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.newsSource.CreateNewsSourceRequest;
import dev.dani.strong.newsfeed.model.dto.request.newsSource.UpdateNewsSourceRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.entity.NewsSource;

import java.util.List;

public interface NewsSourceService {

    CreatedResponse create(CreateNewsSourceRequest createRequest);

    NewsSource fetchById(Long id);

    NewsSource fetchBySourceName(String sourceName);

    List<NewsSource> fetchAll();

    NewsSource updateById(Long id, UpdateNewsSourceRequest updateRequest);

    DeletedResponse deleteById(Long id);
}
