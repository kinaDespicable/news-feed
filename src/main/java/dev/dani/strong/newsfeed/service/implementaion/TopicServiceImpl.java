package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.repository.TopicRepository;
import dev.dani.strong.newsfeed.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

}
