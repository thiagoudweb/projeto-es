package br.edu.ufape.plataforma.mentoria.dto;
import br.edu.ufape.plataforma.mentoria.util.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String name;

    @NotBlank(message = "O e-mail não pode ser vazio")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve possuir pelo menos 8 caracteres")
    private String password;

    
    private UserRole role;

    public UserDTO() {
        }

    public UserDTO(String name, String email, String password, UserRole role) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
