package io.challenge.bestmatched.restaurants.infra;

import io.challenge.bestmatched.restaurants.infra.exception.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String INVALID_REQUEST = "Invalid Request";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ProblemDetail> handleHttpMessageNotReadableException(final HttpMessageNotReadableException exception) {
        log.error("[ERROR]: {}", exception.getMessage());
        final ProblemDetail problemDetail = ProblemDetail.forStatus(BAD_REQUEST);
        problemDetail.setTitle(INVALID_REQUEST);
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail("The request body is invalid or malformed.");

        return ResponseEntity
                .badRequest()
                .body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        log.error("[ERROR]: {}", exception.getMessage());
        final ProblemDetail problemDetail = ProblemDetail.forStatus(BAD_REQUEST);
        final String errors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "%s: %s".formatted(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(joining(", "));

        problemDetail.setTitle(INVALID_REQUEST);
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(errors);
        return ResponseEntity
                .badRequest()
                .body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> handleGenericException(final Exception exception) {
        log.error("[ERROR]: {}", exception.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(new InternalErrorException("Internal Server Error", INTERNAL_SERVER_ERROR).getBody());
    }
}
