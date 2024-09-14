package fr.ynov.shary.DTO;

import fr.ynov.shary.models.Competence;
import fr.ynov.shary.models.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class UserDTO {

    @Setter
    @NotEmpty(message = "Name may not be empty")
    private String username;

    @Setter
    @NotEmpty(message = "Password may not be empty")
    private String password;

    @Setter
    @NotEmpty(message = "Email may not be empty")
    private String email;

    @Setter
    @NotEmpty(message = "Roles may not be empty")
    private List<Role> roles;

    @Setter
    private List<Competence> competences;

    @Setter
    private List<Competence> wantedCompetences;

    @Setter
    private String linkedin;


    public UserDTO(String username, String password, String email, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public UserDTO() {
    }

}
