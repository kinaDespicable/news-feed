package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTopicIgnoreCase(String topic);

    Optional<Topic> findTopicByTopicEqualsIgnoreCase(String topicName);

    @NonNull
    @Query(value = "SELECT * FROM topic ORDER BY id", nativeQuery = true)
    List<Topic> findAll();

}
