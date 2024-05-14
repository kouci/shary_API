package fr.ynov.shary.DTO;

import fr.ynov.shary.models.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserDTO {

    @Getter
    @Setter
    @NotEmpty(message = "Name may not be empty")
    private String username;

    @Getter
    @Setter
    @NotEmpty(message = "Password may not be empty")
    private String password;

    @Getter
    @Setter
    @NotEmpty(message = "Email may not be empty")
    private String email;
    private List<Role> roles;

    public UserDTO(String username, String password, String email, List<Role> role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = role;
    }

    public UserDTO() {
    }

    public List<Role> getRole() {
        return roles;
    }
}
