package io.challenge.bestmatched.restaurants.infra.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDateTime;

import static org.springframework.http.ProblemDetail.forStatusAndDetail;

public class InternalErrorException extends ErrorResponseException {

    public InternalErrorException(final String message, final HttpStatusCode status) {
        super(status, asProblemDetail(message, status), null);
    }

    private static ProblemDetail asProblemDetail(final String message, final HttpStatusCode status) {
        ProblemDetail problemDetail = forStatusAndDetail(status, message);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());
        return problemDetail;
    }
}