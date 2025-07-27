package br.edu.ufape.plataforma.mentoria.exceptions;

public class MentoredNotFoundException extends Exception {
    public MentoredNotFoundException(String message) {
        super(message);
    }

    public MentoredNotFoundException(Long id) {
        super("Mentored not found with id: " + id);
    }

    public MentoredNotFoundException() {
        super("Mentored not found");
    }
}
