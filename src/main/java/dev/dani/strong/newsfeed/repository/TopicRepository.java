package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
