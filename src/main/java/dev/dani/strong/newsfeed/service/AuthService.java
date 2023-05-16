package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.authentication.AuthenticationRequest;
import dev.dani.strong.newsfeed.model.dto.request.authentication.RegistrationRequest;
import dev.dani.strong.newsfeed.model.dto.response.AuthenticationResponse;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;

public interface AuthService {
    CreatedResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest authRequest);
}
