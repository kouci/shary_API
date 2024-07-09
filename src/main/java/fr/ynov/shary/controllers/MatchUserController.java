package fr.ynov.shary.controllers;

import fr.ynov.shary.DTO.MatchUserDTO;
import fr.ynov.shary.models.MatchUser;
import fr.ynov.shary.models.Response;
import fr.ynov.shary.services.MatchUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/match-users")
public class MatchUserController {

    @Autowired
    private MatchUserService matchUserService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody MatchUserDTO matchUserDTO, BindingResult bindingResult) {


        Long userId = matchUserService.createMatchUser(modelMapper.map(matchUserDTO, MatchUser.class));

        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", userId))
                .message("match users successfully")
                .status(HttpStatus.CREATED)
                .build());
    }

    @GetMapping
    public List<MatchUser> getAllUsers() {
        return ResponseEntity.ok(matchUserService.getAll()).getBody();
    }

    @GetMapping("/{id}")
    public MatchUser getMatchUser(@PathVariable long id) {
        return ResponseEntity.ok(matchUserService.getMatchUser(id)).getBody();
    }

    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody MatchUserDTO matchUserDTO) {
        Long userId = matchUserService.updateMatchUser(modelMapper.map(matchUserDTO, MatchUser.class));
        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", userId))
                .message("match user updated successfully")
                .status(HttpStatus.OK)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable long id) {
        matchUserService.deleteMatchUser(id);
        return ResponseEntity.ok(Response.builder()
                .message("match user deleted successfully")
                .status(HttpStatus.OK)
                .build());
    }



}
