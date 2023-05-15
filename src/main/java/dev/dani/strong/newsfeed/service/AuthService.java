package dev.dani.strong.newsfeed.service;

import dev.dani.strong.newsfeed.model.dto.request.RegistrationRequest;
import dev.dani.strong.newsfeed.model.dto.response.CreatedResponse;

public interface AuthService {
    CreatedResponse register(RegistrationRequest request);
}
