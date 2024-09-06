package fr.ynov.shary.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "username", unique = true)
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
    @Setter
    @OneToMany(mappedBy = "userRequester", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MatchUser> requesterMatch = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "userAccepter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MatchUser> accepterMatch = new HashSet<>();

    @Getter
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

    @Getter
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_competences",joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="compt_id"))
    private List<Competence> competences;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserLog> userLogs = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::getRole)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
