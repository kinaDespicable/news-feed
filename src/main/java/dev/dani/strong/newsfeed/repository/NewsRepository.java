package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.News;
import dev.dani.strong.newsfeed.model.entity.NewsSource;
import dev.dani.strong.newsfeed.model.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findNewsByNewsSource(NewsSource newsSource, Pageable pageable);
    Page<News> findNewsByTopic(Topic topic, Pageable pageable);

    Integer countByNewsSource(NewsSource source);
}
