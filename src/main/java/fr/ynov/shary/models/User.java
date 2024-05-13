package fr.ynov.shary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "username")
    @NotEmpty(message = "Name may not be empty")
    private String username;

    @Getter
    @Setter
    @Column(name = "password")
    @NotEmpty(message = "Password may not be empty")
    private String password;

    @Getter
    @Column(name = "email")
    @Setter
    @NotEmpty(message = "Email may not be empty")
    private String email;

    @Getter
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER) @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

}
