package br.edu.ufape.plataforma.mentoria.exceptions;

public class MentorNotFoundException extends Exception {
    public MentorNotFoundException(String message) {
        super(message);
    }

    public MentorNotFoundException(Long id) {
        super("Mentor not found with id: " + id);
    }

    public MentorNotFoundException() {
        super("Mentor not found");
    }
}