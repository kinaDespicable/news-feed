package dev.dani.strong.newsfeed.model.dto.request;

import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;

public interface Validatable<T extends CreateRequest> {

    String EMPTY_STRING = "";

    void checkExistenceForCreation(T request) throws ResourceAlreadyExistException;
}
