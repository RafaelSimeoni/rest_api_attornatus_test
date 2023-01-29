package com.rafael.restapiattornatustest.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundHandlerMethod(
            EntityNotFoundException e, HttpServletRequest request) {

        StandardError se = new StandardError(
                LocalDateTime.now(), 404, "Not Found", e.getMessage(), request.getRequestURI());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErrors(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError errors = new ValidationError(
                LocalDateTime.now(), 400, "Bad Request",
                "field validation error", request.getRequestURI());

        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<StandardError> handleJsonMappingException(JsonMappingException e, HttpServletRequest request) {
        if (e.getCause() instanceof DateTimeParseException) {
            StandardError se = new StandardError(
                    LocalDateTime.now(), 400, "Bad Request",
                    "Date field must have the format 'yyyy-mm-dd'", request.getRequestURI());

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(
                LocalDateTime.now(), 400, "Bad Request", null, request.getRequestURI()));
    }
}
