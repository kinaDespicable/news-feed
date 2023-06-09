package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.config.jwt.JwtService;
import dev.dani.strong.newsfeed.exception.exceptions.PasswordMismatchException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;
import dev.dani.strong.newsfeed.model.dto.request.Validatable;
import dev.dani.strong.newsfeed.model.dto.request.authentication.AuthenticationRequest;
import dev.dani.strong.newsfeed.model.dto.request.authentication.RegistrationRequest;
import dev.dani.strong.newsfeed.model.dto.response.AuthenticationResponse;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;
import dev.dani.strong.newsfeed.model.entity.User;
import dev.dani.strong.newsfeed.repository.UserRepository;
import dev.dani.strong.newsfeed.service.AuthService;
import dev.dani.strong.newsfeed.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, Validatable<RegistrationRequest> {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public CreatedResponse register(RegistrationRequest request) {

        validate(request);

        var entity = User.builder()
                .firstName(request.firstName().trim())
                .lastName(request.lastName().trim())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(roleService.fetchByRoleName(DEFAULT_ROLE))
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
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        UserDetails user = userDetailsService.loadUserByUsername(authRequest.username());
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }


    @Override
    public void checkExistenceForCreation(RegistrationRequest request) throws ResourceAlreadyExistException {

        var username = request.username().trim();

        if (userRepository.existsByUsernameEqualsIgnoreCase(username)) {
            throw new ResourceAlreadyExistException("Username already taken");
        }
    }

    void validate(RegistrationRequest request) {

        checkExistenceForCreation(request);

        if (!request.password().equals(request.passwordConfirmation())) {
            throw new PasswordMismatchException("Passwords does not match.");
        }

    }

}
