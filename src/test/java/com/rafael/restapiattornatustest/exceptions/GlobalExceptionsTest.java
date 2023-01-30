package com.rafael.restapiattornatustest.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GlobalExceptionsTest {

    @InjectMocks
    private GlobalExceptions globalExceptions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenEntityNotFoundExceptionThenReturnNotFoundAndStandardErrorInstance() {
        ResponseEntity<StandardError>  response = globalExceptions.entityNotFoundHandlerMethod(
                new EntityNotFoundException("Object not found"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Object not found", response.getBody().getMessage());
        assertEquals("Not Found", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }
}
