package br.edu.ufape.plataforma.mentoria.dto;

// import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthDTOTest {

    @Test
    void testNoArgsConstructorAndSettersGetters() {
        AuthDTO dto = new AuthDTO();
        dto.setEmail("test@example.com");
        dto.setPassword("secret123");

        assertEquals("test@example.com", dto.getEmail());
        assertEquals("secret123", dto.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        AuthDTO dto = new AuthDTO("user@domain.com", "mypassword");
        assertEquals("user@domain.com", dto.getEmail());
        assertEquals("mypassword", dto.getPassword());
    }

    @Test
    void testSetEmail() {
        AuthDTO dto = new AuthDTO();
        dto.setEmail("another@mail.com");
        assertEquals("another@mail.com", dto.getEmail());
    }

    @Test
    void testSetPassword() {
        AuthDTO dto = new AuthDTO();
        dto.setPassword("pass");
        assertEquals("pass", dto.getPassword());
    }
}
