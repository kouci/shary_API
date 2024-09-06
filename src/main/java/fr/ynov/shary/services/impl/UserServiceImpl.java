package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.User;
import fr.ynov.shary.repository.UserRepository;
import fr.ynov.shary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    @Override
    public long createUser(User user) {
        logger.info("Creating user: {}", user);

        // Encodage du mot de passe avant de sauvegarder l'utilisateur
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        logger.info("User created with ID: {}", savedUser.getId());
        return savedUser.getId();
    }

    @Override
    public void deleteUser(long id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.info("User with ID: {} deleted", id);
    }

    @Override
    public long updateUser(User user) {
        logger.info("Updating user: {}", user);
        User updatedUser = userRepository.save(user);
        logger.info("User updated with ID: {}", updatedUser.getId());
        return updatedUser.getId();
    }

    @Override
    public User getUser(long id) {
        logger.info("Fetching user with ID: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            logger.info("User found: {}", user);
        } else {
            logger.warn("User with ID: {} not found", id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info("Fetching user with username: {}", username);
        User user = userRepository.findByUsername(username).orElseThrow();
        if (user != null) {
            logger.info("User found: {}", user);
        } else {
            logger.warn("User with username: {} not found", username);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Fetching user with email: {}", email);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            logger.info("User found: {}", user);
        } else {
            logger.warn("User with email: {} not found", email);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();
        logger.info("Number of users found: {}", users.size());
        return users;
    }
}