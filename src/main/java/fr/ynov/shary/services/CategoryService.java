package fr.ynov.shary.services;

import fr.ynov.shary.models.Category;

public interface CategoryService {

    public long createCategory(Category category);

    public void deleteCategory(long id);

    public long updateCategory(Category category);

    public Category getCategory(long id);


}
