package fr.ynov.shary.DTO;



import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CompetenceDTO {

    @Getter
    @Setter
    @NotEmpty(message = "Name may not be empty")
    private String comptenceName;

    @Getter
    @Setter
    @NotEmpty(message = "Description may not be empty")
    private String comptenceDescription;

    @Getter
    @Setter
    @NotEmpty(message = "Archived may not be empty")
    private Boolean archived;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    @Getter
    @Setter
    private Set<Long> categoryIds;

    public CompetenceDTO() {
    }

    public CompetenceDTO(String comptenceName, String comptenceDescription, Boolean archived, Date createdAt, Date updatedAt, Set<Long> categoryIds) {

        this.comptenceName = comptenceName;
        this.comptenceDescription = comptenceDescription;
        this.archived = archived;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryIds = categoryIds;
    }
}
