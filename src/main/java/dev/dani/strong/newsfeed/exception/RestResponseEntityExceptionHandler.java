package dev.dani.strong.newsfeed.exception;

import dev.dani.strong.newsfeed.exception.exceptions.ResourceAlreadyExistException;
import dev.dani.strong.newsfeed.exception.exceptions.ResourceNotFoundException;
import dev.dani.strong.newsfeed.exception.exceptions.RoleFormatException;
import dev.dani.strong.newsfeed.model.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        var responseBody = constructResponse(exception, NOT_FOUND);
        return ResponseEntity.status(NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> resourceAlreadyExistExceptionHandler(ResourceAlreadyExistException exception) {
        var responseBody = constructResponse(exception, CONFLICT);
        return ResponseEntity.status(CONFLICT).body(responseBody);
    }

    @ExceptionHandler(RoleFormatException.class)
    public ResponseEntity<ErrorResponse> roleFormatException(RoleFormatException exception) {
        var responseBody = constructResponse(exception, BAD_REQUEST);
        return ResponseEntity.status(BAD_REQUEST).body(responseBody);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("status", BAD_REQUEST);
        responseBody.put("status_code", BAD_REQUEST.value());
        responseBody.put("timestamp", Instant.now());

        Map<String, Object> validationErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        responseBody.put("validation_errors", validationErrors);

        return ResponseEntity.status(BAD_REQUEST).body(responseBody);
    }


    private ErrorResponse constructResponse(RuntimeException exception, HttpStatus status) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status)
                .statusCode(status.value())
                .message(exception.getMessage())
                .build();
    }

}
