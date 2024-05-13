package fr.ynov.shary.services.impl;

import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.models.User;
import fr.ynov.shary.repository.UserRepository;
import fr.ynov.shary.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserServices {


    @Autowired
    private UserRepository userRepository;
    @Override
    public long createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long updateUser(User user) {
        User updatedUser = userRepository.save(user);
        return updatedUser.getId();
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
