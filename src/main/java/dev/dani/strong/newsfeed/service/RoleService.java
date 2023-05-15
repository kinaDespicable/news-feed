package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.role.CreateRoleRequest;
import dev.dani.strong.newsfeed.model.dto.request.role.UpdateRoleRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.entity.Role;

import java.util.List;

public interface RoleService {

    CreatedResponse create(CreateRoleRequest createRequest);

    Role fetchById(Long id);

    Role fetchByRoleName(String role);

    List<Role> fetchAll();

    Role updateById(Long id, UpdateRoleRequest updateRequest);

    DeletedResponse deleteById(Long id);
}
