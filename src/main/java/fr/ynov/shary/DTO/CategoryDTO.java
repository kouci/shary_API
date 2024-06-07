package fr.ynov.shary.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

public class CategoryDTO {


    @Getter
    @Setter
    @NotEmpty(message = "Category name may not be empty")
    private String categoryName;

    @Getter
    @Setter
    private String categoryDescription;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    @Getter
    @Setter
    private Long parentId;

    @Getter
    @Setter
    private Set<Long> childCategoryIds;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryName, String categoryDescription, Date createdAt, Date updatedAt, Long parentId, Set<Long> childCategoryIds) {

        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.parentId = parentId;
        this.childCategoryIds = childCategoryIds;
    }
}
