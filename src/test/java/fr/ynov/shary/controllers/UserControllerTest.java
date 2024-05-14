package fr.ynov.shary.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.controllers.UserController;
import fr.ynov.shary.models.User;
import fr.ynov.shary.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServices userServices;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private UserDTO userDto;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
    }

    @Test
    void getAllUsers() throws Exception {
        when(userServices.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Ajoutez ici des vérifications supplémentaires selon vos besoins
    }

    @Test
    void getUser() throws Exception {
        when(userServices.getUser(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Ajoutez ici des vérifications supplémentaires selon vos besoins
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Ajoutez ici des vérifications supplémentaires selon vos besoins
    }

    @Test
    void updateUser() throws Exception {
        when(userServices.updateUser(user)).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Ajoutez ici des vérifications supplémentaires selon vos besoins
    }

    @Test
    void getUserByUsername() throws Exception {
        when(userServices.getUserByUsername("testUser")).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/username/testUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Ajoutez ici des vérifications supplémentaires selon vos besoins
    }

    @Test
    void createUser_ValidInput_ReturnsCreated() throws Exception {
        when(userServices.createUser(user)).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void createUser_InvalidInput_ReturnsBadRequest() throws Exception {
        // Création d'un objet BindingResult avec des erreurs simulées
        Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
        errors.reject("username", "Username is required");
        errors.reject("email", "Email is required");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
