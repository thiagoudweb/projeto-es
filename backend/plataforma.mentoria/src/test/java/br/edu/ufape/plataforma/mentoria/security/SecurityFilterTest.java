import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.FilterChain;

import br.edu.ufape.plataforma.mentoria.security.SecurityFilter;
import br.edu.ufape.plataforma.mentoria.security.TokenService;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import br.edu.ufape.plataforma.mentoria.model.User;

class SecurityFilterTest {

    private SecurityFilter filter;
    private TokenService tokenService;
    private UserRepository userRepository;
    private FilterChain filterChain;

    static class TestableSecurityFilter extends SecurityFilter {
        public void callDoFilterInternal(MockHttpServletRequest request, MockHttpServletResponse response, FilterChain chain) throws Exception {
            super.doFilterInternal(request, response, chain);
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        tokenService = mock(TokenService.class);
        userRepository = mock(UserRepository.class);
        filterChain = mock(FilterChain.class);
        filter = new TestableSecurityFilter();

        Field tokenServiceField = SecurityFilter.class.getDeclaredField("tokenService");
        tokenServiceField.setAccessible(true);
        tokenServiceField.set(filter, tokenService);

        Field userRepositoryField = SecurityFilter.class.getDeclaredField("userRepository");
        userRepositoryField.setAccessible(true);
        userRepositoryField.set(filter, userRepository);

        SecurityContextHolder.clearContext();
    }

    @Test
    void testTokenValidoAutenticaUsuario() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid-token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateToken("valid-token")).thenReturn("user@email.com");
        User userDetails = mock(User.class);
        when(userRepository.findByEmail("user@email.com")).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(null);

        ((TestableSecurityFilter)filter).callDoFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testTokenInvalidoNaoAutentica() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer invalid-token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateToken("invalid-token")).thenReturn(null);

        ((TestableSecurityFilter)filter).callDoFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testSemTokenNaoAutentica() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        ((TestableSecurityFilter)filter).callDoFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testUsuarioNaoEncontrado() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid-token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        when(tokenService.validateToken("valid-token")).thenReturn("user@email.com");
        when(userRepository.findByEmail("user@email.com")).thenReturn(null);

        ((TestableSecurityFilter)filter).callDoFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }
}