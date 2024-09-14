package fr.ynov.shary.controllers;

import fr.ynov.shary.DTO.CompetenceDTO;
import fr.ynov.shary.models.Competence;
import fr.ynov.shary.models.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.ynov.shary.services.CompetenceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/competences")
public class ComptetenceController {

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody CompetenceDTO competenceDTO) {

        Long competenceId = competenceService.createCompetence(modelMapper.map(competenceDTO, Competence.class));

        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", competenceId))
                .message("Competance created successfully")
                .status(HttpStatus.CREATED)
                .build());
    }


    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Competence> getAllCompetences() {
        return ResponseEntity.ok(competenceService.getAll()).getBody();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Competence getCompetence(@PathVariable long id) {
        return ResponseEntity.ok(competenceService.getCompetence(id)).getBody();
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody Competence competence) {
        Long competenceId = competenceService.updateCompetence(competence);
        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", competenceId))
                .message("Competance updated successfully")
                .status(HttpStatus.OK)
                .build());
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable long id) {
        competenceService.deleteCompetence(id);
        return ResponseEntity.ok(Response.builder()
                .message("Competance deleted successfully")
                .status(HttpStatus.OK)
                .build());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/add")
    public void addCompetencesIfEmpty() {
        competenceService.addCompetencesIfEmpty();
    }



}
