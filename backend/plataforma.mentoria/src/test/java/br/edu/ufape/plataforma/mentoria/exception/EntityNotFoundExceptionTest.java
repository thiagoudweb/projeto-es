package br.edu.ufape.plataforma.mentoria.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;

public class EntityNotFoundExceptionTest {

    @Test
    void testConstructorWithEntityClassAndId() {
        Class<?> entityClass = String.class;
        Object entityId = 123L;

        EntityNotFoundException exception = new EntityNotFoundException(entityClass, entityId);

        String expectedMessage = "String com ID 123 não foi encontrado.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithEntityClassResourceNameAndValue() {
        Class<?> entityClass = String.class;
        String resourceName = "email";
        String resourceValue = "user@example.com";

        EntityNotFoundException exception = new EntityNotFoundException(entityClass, resourceName, resourceValue);

        String expectedMessage = "String com email 'user@example.com' não foi encontrado.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithCustomMessage() {
        String customMessage = "Custom error message";

        EntityNotFoundException exception = new EntityNotFoundException(customMessage);

        assertEquals(customMessage, exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        EntityNotFoundException exception = new EntityNotFoundException("Test");

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testResponseStatusAnnotation() {
        Class<EntityNotFoundException> exceptionClass = EntityNotFoundException.class;

        ResponseStatus annotation = exceptionClass.getAnnotation(ResponseStatus.class);

        assertNotNull(annotation);
        assertEquals(HttpStatus.NOT_FOUND, annotation.value());
    }
}
