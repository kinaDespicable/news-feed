package dev.dani.strong.newsfeed.repository;

import dev.dani.strong.newsfeed.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
