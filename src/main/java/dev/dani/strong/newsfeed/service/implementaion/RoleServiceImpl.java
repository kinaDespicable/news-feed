package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceNotFoundException;
import dev.dani.strong.newsfeed.exception.exceptions.RoleFormatException;
import dev.dani.strong.newsfeed.model.dto.request.Validatable;
import dev.dani.strong.newsfeed.model.dto.request.role.CreateRoleRequest;
import dev.dani.strong.newsfeed.model.dto.request.role.UpdateRoleRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.DeletedResponse;
import dev.dani.strong.newsfeed.model.entity.Role;
import dev.dani.strong.newsfeed.repository.RoleRepository;
import dev.dani.strong.newsfeed.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService, Validatable<CreateRoleRequest> {

    public static final String ROLE_PREFIX = "ROLE_";

    private final RoleRepository roleRepository;

    @Override
    public CreatedResponse create(CreateRoleRequest createRequest) {

        checkExistenceForCreation(createRequest);

        Role entity = Role.builder()
                .roleName(formatRole(createRequest.role()))
                .build();

        roleRepository.save(entity);

        return CreatedResponse.builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now())
                .data(entity)
                .build();

    }

    @Override
    public Role fetchById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with id: [" + id + "] not found."));
    }

    @Override
    public Role fetchByRoleName(String role) {
        return roleRepository.findByRoleName(formatRole(role))
                .orElseThrow(() -> new ResourceNotFoundException("Role with name: [" + role + "] not found."));
    }

    @Override
    public List<Role> fetchAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateById(Long id, UpdateRoleRequest updateRequest) {
        var roleFromDatabase = fetchById(id);

        if (Objects.nonNull(updateRequest.role()) && !updateRequest.role().equalsIgnoreCase(EMPTY_STRING)) {
            roleFromDatabase.setRoleName(formatRole(updateRequest.role()));
        }

        return roleRepository.save(roleFromDatabase);
    }

    @Override
    public DeletedResponse deleteById(Long id) {
        var roleFromDatabase = fetchById(id);
        roleRepository.delete(roleFromDatabase);

        return DeletedResponse.builder()
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(Instant.now())
                .deleted(true)
                .data(roleFromDatabase)
                .build();
    }

    @Override
    public void checkExistenceForCreation(CreateRoleRequest request) throws ResourceAlreadyExistException {
        var roleName = formatRole(request.role());

        if (roleRepository.existsByRoleName(roleName)) {
            throw new ResourceAlreadyExistException("Role: [" + request.role() + "] already exists");
        }
    }

    private String formatRole(String roleName) {

        var role = roleName.trim().toUpperCase();

        if (role.isEmpty()) {
            throw new RoleFormatException("Role name cannot be empty.");
        }
        if (role.startsWith(ROLE_PREFIX) && role.length() == 5) {
            throw new RoleFormatException("Invalid role format.");
        }
        if (!role.startsWith(ROLE_PREFIX)) {
            return ROLE_PREFIX + role;
        }

        return role;
    }

}
