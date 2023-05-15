package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
