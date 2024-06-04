package fr.ynov.shary.services;

import fr.ynov.shary.models.User;

public interface CategoryServices {

    public long createCategory(User user);

    public void deleteCategory(long id);

    public long updateCategory(User user);

    public User getCategory(long id);


}
