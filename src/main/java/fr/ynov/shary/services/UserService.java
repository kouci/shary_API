package fr.ynov.shary.services;

import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.models.User;

import java.util.List;

public interface UserService {
    public long createUser(UserDTO user);

    public void deleteUser(long id);

    public long updateUser(UserDTO user, Long id);

    public User getUser(long id);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);


    public List<User> getAllUsers();
}
