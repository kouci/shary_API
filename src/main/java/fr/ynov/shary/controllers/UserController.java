package fr.ynov.shary.controllers;

import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.models.Response;
import fr.ynov.shary.models.User;
import fr.ynov.shary.services.UserService;
import fr.ynov.shary.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.List.of;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserValidator userValidator;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody UserDTO userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Response.builder().message("Invalid user").status(HttpStatus.BAD_REQUEST).build());
        }

        Long userId = userService.createUser(userDto);

        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", userId))
                .message("User created successfully")
                .status(HttpStatus.CREATED)
                .build());
    }


    @CrossOrigin(origins = "*")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable long id, @RequestBody UserDTO user) {
        Long userId = userService.updateUser(user,id);
        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", userId))
                .message("User updated successfully")
                .status(HttpStatus.OK)
                .build());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
