package br.edu.ufape.plataforma.mentoria.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.edu.ufape.plataforma.mentoria.exceptions.TokenCreationException;
import br.edu.ufape.plataforma.mentoria.model.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                .withIssuer("Mentoria")
                .withSubject(user.getUsername())
                .withClaim("role", user.getRole().name())
                .withClaim("userId", user.getId())
                .withExpiresAt(this.getExpirationAt())
                .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new TokenCreationException("Error creating token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("Mentoria")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant getExpirationAt() {
        return Instant.now().plusSeconds(7200);
    }
}
