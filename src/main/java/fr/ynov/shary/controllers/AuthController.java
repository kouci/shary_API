package fr.ynov.shary.controllers;

import fr.ynov.shary.DTO.ChangePasswordDTO;
import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.models.Response;
import fr.ynov.shary.models.AuthResponse;
import fr.ynov.shary.models.AuthRequest;
import fr.ynov.shary.models.User;
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
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),   authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        final Long id = userService.getUserByUsername(authRequest.getUsername()).getId();

        return ResponseEntity.ok(new AuthResponse(jwt, id));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDto, BindingResult bindingResult) {
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
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto) throws Exception {
        User user = userService.getUser(dto.getId());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),   dto.getOldPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .message("Incorrect password")
                    .status(HttpStatus.UNAUTHORIZED)
                    .build());
        }
        boolean isSuccess = userService.changePassword(dto);
        if (!isSuccess) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .message("Failed to change password")
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
        return ResponseEntity.ok(Response.builder()
                .message("Password changed successfully")
                .status(HttpStatus.OK)
                .build());
    }

}
