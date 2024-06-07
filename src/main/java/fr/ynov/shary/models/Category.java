package fr.ynov.shary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catecories")
public class Category {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long category_id;

    @Getter
    @Setter
    @Column(name ="category_name")
    private String categoryName;

    @Getter
    @Setter
    @Column(name = "category_description")
    private String categoryDescription;

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
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Getter
    @Setter
    @OneToMany(mappedBy = "parent")
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    Set<Competence> competences;
}
