package fr.ynov.shary.services;

import fr.ynov.shary.models.User;

public interface UserLogServices {

    public long createUserLog(User user);

    public void deleteUserLog(long id);

    public long updateUserLog(User user);

    public User getUserLog(long id);
}
