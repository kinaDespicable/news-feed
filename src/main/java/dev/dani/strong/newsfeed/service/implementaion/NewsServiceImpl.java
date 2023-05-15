package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.repository.NewsRepository;
import dev.dani.strong.newsfeed.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

}
