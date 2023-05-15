package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
