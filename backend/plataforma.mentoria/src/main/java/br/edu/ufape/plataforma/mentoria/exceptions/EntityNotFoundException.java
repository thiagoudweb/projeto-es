package br.edu.ufape.plataforma.mentoria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // HTTP 404
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor to create an exception for a not-found entity.
     * 
     * @param entityClass The class of the entity that was not found.
     * @param entityId    The identifier of the entity that was not found.
     */
    public EntityNotFoundException(Class<?> entityClass, Object entityId) {
        super(String.format("%s com ID %s não encontrado(a).", entityClass.getSimpleName(), entityId.toString()));
    }

    /**
     * Constructor to create an exception for a not-found entity.
     * 
     * @param entityClass   The class of the entity that was not found.
     * @param entityId      The identifier of the entity that was not found.
     * @param resourceName  The name of the resource (e.g., "User", "Mentored").
     * @param resourceValue The value of the resource that was not found.
     */
    public EntityNotFoundException(Class<?> entityClass, String resourceName, String resourceValue) {
        super(String.format("%s com %s '%s' não encontrado(a).", entityClass.getSimpleName(), resourceName,
                resourceValue));
    }

    /**
     * Constructor with a custom message.
     * 
     * @param message The detail message.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}