package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {

    @NonNull
    @Query(value = "SELECT * FROM news_source ORDER BY id", nativeQuery = true)
    List<NewsSource> findAll();

    boolean existsByNameEqualsIgnoreCase(String name);

    boolean existsBySourceNameEqualsIgnoreCase(String sourceName);

    Optional<NewsSource> findBySourceNameEqualsIgnoreCase(String sourceName);
}
