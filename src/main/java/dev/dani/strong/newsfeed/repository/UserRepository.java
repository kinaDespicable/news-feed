package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.Role;
import dev.dani.strong.newsfeed.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsernameEqualsIgnoreCase(String username);

    List<User> findAllByRole(Role role);

    @NonNull
    @Query(value = "SELECT * FROM site_user ORDER BY id", nativeQuery = true)
    List<User> findAll();

}
