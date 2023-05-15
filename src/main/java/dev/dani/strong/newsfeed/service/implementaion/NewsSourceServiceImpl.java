package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.repository.NewsSourceRepository;
import dev.dani.strong.newsfeed.service.NewsSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsSourceServiceImpl implements NewsSourceService {

    private final NewsSourceRepository newsSourceRepository;

}
