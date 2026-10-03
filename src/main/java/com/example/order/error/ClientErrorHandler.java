package com.example.order.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class ClientErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ClientErrorHandler.class);

    @ExceptionHandler(ResourceAccessException.class)
    public ProblemDetail handleTimeout(ResourceAccessException ex) {
        log.error("ResourceAccessException: {}", ex.getMessage());
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.GATEWAY_TIMEOUT, ex.getMessage());
        problem.setTitle("Catalog Service Timeout");
        return problem;
    }
}
