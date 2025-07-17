package br.edu.ufape.plataforma.mentoria.controller;

import br.edu.ufape.plataforma.mentoria.dto.UserDTO;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.security.TokenService;
import br.edu.ufape.plataforma.mentoria.service.AuthService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager,
                          AuthService authService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDTO userDTO){
        User resgistredUser = this.authService.register(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(resgistredUser);
    }
}
