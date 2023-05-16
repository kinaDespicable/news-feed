package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.exception.exceptions.PasswordMismatchException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceNotFoundException;
import dev.dani.strong.newsfeed.model.dto.request.Validatable;
import dev.dani.strong.newsfeed.model.dto.request.user.CreateUserRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.dto.response.user.UserListResponse;
import dev.dani.strong.newsfeed.model.entity.User;
import dev.dani.strong.newsfeed.repository.UserRepository;
import dev.dani.strong.newsfeed.service.RoleService;
import dev.dani.strong.newsfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, Validatable<CreateUserRequest> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public CreatedResponse create(CreateUserRequest createRequest) {
        validate(createRequest);

        var role = roleService.fetchByRoleName(createRequest.role());

        var entity = User.builder()
                .firstName(createRequest.firstName().trim())
                .lastName(createRequest.lastName().trim())
                .username(createRequest.username().trim())
                .password(passwordEncoder.encode(createRequest.password()))
                .role(role)
                .registeredAt(LocalDate.now())
                .build();

        userRepository.save(entity);

        return CreatedResponse.builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(Instant.now())
                .data(entity)
                .build();
    }

    @Override
    public User fetchById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: [" + id + "] not found"));
    }

    @Override
    public User fetchByUsername(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User: [" + userName + "] not found"));
    }

    @Override
    public List<UserListResponse> fetchAll() {
        return userRepository.findAll().stream()
                .map(singleUser -> UserListResponse.builder()
                        .id(singleUser.getId())
                        .firstName(singleUser.getFirstName())
                        .lastName(singleUser.getLastName())
                        .username(singleUser.getUsername())
                        .role(singleUser.getRole().getRoleName())
                        .build()).toList();
    }

    @Override
    public List<UserListResponse> fetchByUserByRole(String roleName) {
        var role = roleService.fetchByRoleName(roleName);
        return userRepository.findAllByRole(role).stream()
                .map(singleUser -> UserListResponse.builder()
                        .id(singleUser.getId())
                        .firstName(singleUser.getFirstName())
                        .lastName(singleUser.getLastName())
                        .username(singleUser.getUsername())
                        .role(singleUser.getRole().getRoleName())
                        .build()).toList();
    }


    @Override
    public void checkExistenceForCreation(CreateUserRequest request) throws ResourceAlreadyExistException {
        if (userRepository.existsByUsernameEqualsIgnoreCase(request.username())) {
            throw new ResourceAlreadyExistException("User: [" + request.username() + "] already exists");
        }
    }

    void validate(CreateUserRequest request) {

        checkExistenceForCreation(request);

        if (!request.password().equals(request.passwordConfirmation())) {
            throw new PasswordMismatchException("Passwords does not match.");
        }

    }
}
