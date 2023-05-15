package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}
