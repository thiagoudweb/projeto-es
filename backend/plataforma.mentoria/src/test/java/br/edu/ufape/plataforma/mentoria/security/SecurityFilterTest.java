package br.edu.ufape.plataforma.mentoria.security;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private SecurityFilter securityFilter;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testTokenValidoAutenticaUsuario() throws Exception {
        String token = "valid-token";
        String email = "user@email.com";
        User mockUser = mock(User.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(tokenService.validateToken(token)).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        securityFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(mockUser, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    void testTokenInvalidoNaoAutentica() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalid-token");
        when(tokenService.validateToken("invalid-token")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testHeaderInvalidoNaoAutentica() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Basic some_credentials");

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testSemTokenNaoAutentica() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testUsuarioNaoEncontrado() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer valid-token");
        when(tokenService.validateToken("valid-token")).thenReturn("user@email.com");
        when(userRepository.findByEmail("user@email.com")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testTokenValidoLoginVazio() throws Exception {
        String token = "valid-token";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(tokenService.validateToken(token)).thenReturn("");

        securityFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testRecoverTokenNullHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);
        var method = SecurityFilter.class.getDeclaredMethod("recoverToken", HttpServletRequest.class);
        method.setAccessible(true);
        assertNull(method.invoke(securityFilter, request));
    }

    @Test
    void testRecoverTokenEmptyHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("");
        var method = SecurityFilter.class.getDeclaredMethod("recoverToken", HttpServletRequest.class);
        method.setAccessible(true);
        assertNull(method.invoke(securityFilter, request));
    }

    @Test
    void testRecoverTokenInvalidHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Basic abc");
        var method = SecurityFilter.class.getDeclaredMethod("recoverToken", HttpServletRequest.class);
        method.setAccessible(true);
        assertNull(method.invoke(securityFilter, request));
    }

    @Test
    void testRecoverTokenValidHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer abc123");
        var method = SecurityFilter.class.getDeclaredMethod("recoverToken", HttpServletRequest.class);
        method.setAccessible(true);
        assertEquals("abc123", method.invoke(securityFilter, request));
    }
}