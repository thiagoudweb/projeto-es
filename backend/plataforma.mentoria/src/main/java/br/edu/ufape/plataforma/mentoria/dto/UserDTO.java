package br.edu.ufape.plataforma.mentoria.dto;
import br.edu.ufape.plataforma.mentoria.util.UserRole;
public class UserDTO {

    private String name;
    private String email;
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
