package dev.dani.strong.newsfeed.controller;

import dev.dani.strong.newsfeed.model.dto.request.user.CreateUserRequest;
import dev.dani.strong.newsfeed.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest createRequest) {
        return new ResponseEntity<>(userService.create(createRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.fetchById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> fetchByUsername(@RequestParam(name = "username", required = false) Optional<String> username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = username.orElse(authentication.getName());
        return new ResponseEntity<>(userService.fetchByUsername(userName), HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<?> fetchByRole(@RequestParam(name = "role", required = false) Optional<String> role) {
        String roleName = role.orElse(DEFAULT_ROLE);
        return new ResponseEntity<>(userService.fetchByUserByRole(roleName), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.fetchAll(), HttpStatus.OK);
    }

}
