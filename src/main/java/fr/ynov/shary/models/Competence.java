package fr.ynov.shary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "competences")
@NoArgsConstructor
@AllArgsConstructor
public class Competence {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long comptence_id;

    @Getter
    @Setter
    @NotEmpty(message = "Name may not be empty")
    @Column(name = "compt_name")
    private String comptenceName;

    @Getter
    @Setter
    @NotEmpty(message = "Name may not be empty")
    @Column(name = "comp_description")
    private String comptenceDescription;

    @Getter
    @Setter
    @NotEmpty(message = "Name may not be empty")
    @Column(name = "archived")
    private Boolean archived;

    @Getter
    @Setter
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "competences_categories",
            joinColumns = @JoinColumn(name = "compt_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "competences")
    private List<User> users;

}
