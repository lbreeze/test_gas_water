package ru.danch.test.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.danch.test.dto.ExceptionDto;
import ru.danch.test.exceptions.ValidationException;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionProcessor extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionProcessor.class);

    private boolean isBindingException(Exception ex) {
        return Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::getClassName)
                .anyMatch(name -> WebDataBinder.class.getName().equals(name));
    }

    private ResponseEntity<ExceptionDto> wrapped(Exception exception, HttpStatus status) {
        var message = Optional.ofNullable(exception.getMessage()).orElseGet(() -> exception.getClass().getSimpleName());
        return wrapped(message, status);
    }
    private ResponseEntity<ExceptionDto> wrapped(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ExceptionDto(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        var status = INTERNAL_SERVER_ERROR;
        if (ex instanceof NotWritablePropertyException && isBindingException(ex)) {
            status = BAD_REQUEST;
        }
        return wrapped(ex, status);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDto> handleValidationException(ValidationException ex) {
        LOG.error(ex.getMessage(), ex);

        return wrapped(ex, BAD_REQUEST);
    }

}
