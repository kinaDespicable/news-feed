package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.exception.exceptions.ResourceNotFoundException;
import dev.dani.strong.newsfeed.model.dto.request.news.CreateNewsRequest;
import dev.dani.strong.newsfeed.model.dto.request.news.UpdateNewsRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.dto.response.news.SingleNews;
import dev.dani.strong.newsfeed.model.entity.News;
import dev.dani.strong.newsfeed.repository.NewsRepository;
import dev.dani.strong.newsfeed.service.NewsService;
import dev.dani.strong.newsfeed.service.NewsSourceService;
import dev.dani.strong.newsfeed.service.TopicService;
import dev.dani.strong.newsfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static dev.dani.strong.newsfeed.model.dto.request.Validatable.EMPTY_STRING;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private static final int MAX_PAGE_SIZE = 10;
    private static final String DEFAULT_SORING_ATTRIBUTE = "id";

    private final NewsRepository newsRepository;
    private final TopicService topicService;
    private final UserService userService;
    private final NewsSourceService newsSourceService;

    @Override
    public CreatedResponse create(CreateNewsRequest createRequest, Authentication authentication) {

        var author = userService.fetchByUsername((String) authentication.getPrincipal());
        var newsSource = newsSourceService.fetchBySourceName(createRequest.newsSource());
        var topic = topicService.fetchByTopicName(createRequest.topic());

        var entity = News.builder()
                .title(createRequest.title())
                .content(createRequest.content())
                .topic(topic)
                .author(author)
                .newsSource(newsSource)
                .publishedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        newsRepository.save(entity);

        var createdNews = newsMapper(entity);

        return CreatedResponse.builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now())
                .data(createdNews)
                .build();
    }

    @Override
    public SingleNews fetchById(Long id) {
        return newsRepository.findById(id).map(this::newsMapper)
                .orElseThrow(() -> new ResourceNotFoundException("News with id: [" + id + "] not found"));
    }

    @Override
    public List<SingleNews> fetchAll(Optional<Integer> page, Optional<Integer> size, Optional<String> sort) {

        Pageable pageable = getPageable(page, size, sort);
        List<News> newsEntities = newsRepository.findAll(pageable).getContent();

        return newsEntities.stream()
                .map(this::newsMapper)
                .toList();
    }

    @Override
    public List<SingleNews> fetchBySourceId(Long id, Optional<Integer> page, Optional<Integer> size, Optional<String> sort) {

        Pageable pageable = getPageable(page, size, sort);

        var newsSource = newsSourceService.fetchById(id);

        List<News> newsEntities = newsRepository.findNewsByNewsSource(newsSource, pageable).getContent();

        return newsEntities.stream()
                .map(this::newsMapper)
                .toList();
    }

    @Override
    public List<SingleNews> fetchByTopicId(Long id, Optional<Integer> page, Optional<Integer> size, Optional<String> sort) {

        Pageable pageable = getPageable(page, size, sort);

        var topic = topicService.fetchById(id);

        List<News> newsEntities = newsRepository.findNewsByTopic(topic, pageable).getContent();

        return newsEntities.stream()
                .map(this::newsMapper)
                .toList();
    }

    @Override
    public SingleNews updateById(Long id, UpdateNewsRequest updateRequest) {
        var entity = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News with id: [" + id + "] not found."));

        if (Objects.nonNull(updateRequest.title()) && !updateRequest.title().equalsIgnoreCase(EMPTY_STRING)) {
            entity.setTitle(updateRequest.title());
            entity.setUpdatedAt(LocalDateTime.now());
        }
        if (Objects.nonNull(updateRequest.content()) && !updateRequest.content().equalsIgnoreCase(EMPTY_STRING)) {
            entity.setContent(updateRequest.content());
            entity.setUpdatedAt(LocalDateTime.now());
        }

        var updated = newsRepository.save(entity);

        return newsMapper(updated);
    }

    @Override
    public DeletedResponse deleteById(Long id) {
        var entity = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News with id: [" + id + "] not found."));

        newsRepository.delete(entity);

        return DeletedResponse.builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(Instant.now())
                .deleted(true)
                .data(entity)
                .build();
    }

    private Pageable getPageable(Optional<Integer> page, Optional<Integer> size, Optional<String> sort) {
        Integer _page = page.orElse(0);
        Integer _size = size.orElse(MAX_PAGE_SIZE);
        String _sort = sort.orElse(DEFAULT_SORING_ATTRIBUTE);

        return PageRequest.of(_page, _size, Sort.by(Sort.Direction.ASC, _sort));
    }

    private SingleNews newsMapper(News entity) {
        return SingleNews.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .topic(entity.getTopic().getTopic())
                .author(entity.getAuthor().getUsername())
                .newsSource(entity.getNewsSource().getSourceName())
                .createdAt(entity.getPublishedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
