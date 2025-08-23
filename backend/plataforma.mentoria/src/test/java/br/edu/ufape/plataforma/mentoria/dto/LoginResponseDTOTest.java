package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseDTOTest {

    @Test
    void testConstructorAndGetToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.test.token";

        LoginResponseDTO dto = new LoginResponseDTO(token);

        assertEquals(token, dto.getToken());
    }

    @Test
    void testConstructorWithNullToken() {
        LoginResponseDTO dto = new LoginResponseDTO(null);

        assertNull(dto.getToken());
    }

    @Test
    void testConstructorWithEmptyToken() {
        String emptyToken = "";

        LoginResponseDTO dto = new LoginResponseDTO(emptyToken);

        assertEquals("", dto.getToken());
    }

    @Test
    void testGetTokenReturnsSameValue() {
        String token = "sample.jwt.token.value";
        LoginResponseDTO dto = new LoginResponseDTO(token);

        assertEquals(token, dto.getToken());
    }
}
