package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.user.CreateUserRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.user.UserListResponse;
import dev.dani.strong.newsfeed.model.entity.User;

import java.util.List;

public interface UserService {
    CreatedResponse create(CreateUserRequest createRequest);

    User fetchById(Long id);

    User fetchByUsername(String userName);

    List<UserListResponse> fetchAll();

    List<UserListResponse> fetchByUserByRole(String roleName);
}
