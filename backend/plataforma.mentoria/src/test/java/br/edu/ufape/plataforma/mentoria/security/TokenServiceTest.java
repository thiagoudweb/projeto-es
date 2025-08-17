package br.edu.ufape.plataforma.mentoria.security;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.enums.UserRole;

class TokenServiceTest {

    private TokenService tokenService;
    private final String secret = "test-secret";

    @BeforeEach
    void setUp() throws Exception {
        tokenService = new TokenService();
        Field secretField = TokenService.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(tokenService, secret);
    }

    @Test
    void testGerarTokenValido() {
        User user = new User();
        user.setId(1L);
        user.setEmail("user@email.com");
        user.setRole(UserRole.MENTOR);

        String token = tokenService.generateToken(user);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testValidarTokenValido() {
        User user = new User();
        user.setId(1L);
        user.setEmail("user@email.com");
        user.setRole(UserRole.MENTOR);

        String token = tokenService.generateToken(user);
        String subject = tokenService.validateToken(token);
        assertEquals("user@email.com", subject);
    }

    @Test
    void testValidarTokenInvalido() {
        String invalidToken = "token.invalido";
        String subject = tokenService.validateToken(invalidToken);
        assertNull(subject);
    }

    @Test
    void testGerarTokenComEmailNulo() {
        User user = new User();
        user.setId(1L);
        user.setEmail(null);

        assertThrows(RuntimeException.class, () -> tokenService.generateToken(user));
    }

    @Test
    void testGerarTokenComSecretNulo() throws Exception {
        Field secretField = TokenService.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(tokenService, null);

        User user = new User();
        user.setId(1L);
        user.setEmail("user@email.com");
        user.setRole(UserRole.MENTOR);

        assertThrows(RuntimeException.class, () -> tokenService.generateToken(user));
    }
}