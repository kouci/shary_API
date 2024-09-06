package fr.ynov.shary.controllers;

import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.models.Response;
import fr.ynov.shary.models.User;
import fr.ynov.shary.models.AuthResponse;
import fr.ynov.shary.models.AuthRequest;
import fr.ynov.shary.repository.UserRepository;
import fr.ynov.shary.security.JwtTokenUtil;
import fr.ynov.shary.services.CustomUserDetailsService;
import fr.ynov.shary.services.UserService;
import fr.ynov.shary.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Response.builder().message("Invalid user").status(HttpStatus.BAD_REQUEST).build());
        }

        Long userId = userService.createUser(modelMapper.map(userDto, User.class));

        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", userId))
                .message("User created successfully")
                .status(HttpStatus.CREATED)
                .build());
    }
}
