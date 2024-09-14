package fr.ynov.shary.controllers;


import fr.ynov.shary.DTO.CategoryDTO;
import fr.ynov.shary.DTO.UserLogDTO;
import fr.ynov.shary.models.Category;
import fr.ynov.shary.models.Response;
import fr.ynov.shary.models.UserLog;
import fr.ynov.shary.services.UserLogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/userLogs")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<Response> createCategory(@RequestBody UserLogDTO userLogDto) {
        Long categoryId = userLogService.createUserLog(modelMapper.map(userLogDto, UserLog.class));

        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", categoryId))
                .message("Category created successfully")
                .status(HttpStatus.CREATED)
                .build());
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<UserLogDTO>> getAllUserLogs() {
        List<UserLog> userLogs = userLogService.getAllUserLogs();
        List<UserLogDTO> userLogDTOs = userLogs.stream()
                .map(userLog -> modelMapper.map(userLog, UserLogDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userLogDTOs);
    }
}
