package com.inheritchain.messages.controller;

import com.inheritchain.messages.model.exceptions.ErrorMessage;
import com.inheritchain.messages.model.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorMessage badRequest(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NullPointerException.class, NotFoundException.class})
    public ErrorMessage notFound(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        log.error(Arrays.toString(ex.getStackTrace()));
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
    }
}
