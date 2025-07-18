package br.edu.ufape.plataforma.mentoria.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDTO {

    @NotBlank(message = "E-mail não pode ser vazio")
    private String email;

    @NotBlank(message = "Senha não pode ser vazio")
    private String password;

    public AuthDTO(){
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
