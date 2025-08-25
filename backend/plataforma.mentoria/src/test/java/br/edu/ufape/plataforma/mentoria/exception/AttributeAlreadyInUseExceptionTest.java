package br.edu.ufape.plataforma.mentoria.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;

public class AttributeAlreadyInUseExceptionTest {

    @Test
    void testConstructorWithAttributeNameAndEntityClass() {
        String attributeName = "Email";
        Class<?> entityClass = String.class;

        AttributeAlreadyInUseException exception = new AttributeAlreadyInUseException(attributeName, entityClass);

        String expectedMessage = "Email is already in use for entity String.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testConstructorWithAttributeNameValueAndEntityClass() {
        String attributeName = "Email";
        String attributeValue = "user@example.com";
        Class<?> entityClass = String.class;

        AttributeAlreadyInUseException exception = new AttributeAlreadyInUseException(attributeName, attributeValue,
                entityClass);

        String expectedMessage = "Email 'user@example.com' is already in use for entity String.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testExceptionInheritance() {
        AttributeAlreadyInUseException exception = new AttributeAlreadyInUseException("CPF", String.class);

        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void testResponseStatusAnnotation() {
        Class<AttributeAlreadyInUseException> exceptionClass = AttributeAlreadyInUseException.class;

        ResponseStatus annotation = exceptionClass.getAnnotation(ResponseStatus.class);

        assertNotNull(annotation);
        assertEquals(HttpStatus.CONFLICT, annotation.value());
    }
}
