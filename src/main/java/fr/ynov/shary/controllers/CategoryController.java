package fr.ynov.shary.controllers;

import fr.ynov.shary.DTO.CategoryDTO;
import fr.ynov.shary.DTO.UserDTO;
import fr.ynov.shary.models.Category;
import fr.ynov.shary.models.Response;
import fr.ynov.shary.models.User;
import fr.ynov.shary.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDTO categoryDto) {
        Long categoryId = categoryService.createCategory(modelMapper.map(categoryDto, Category.class));

        return ResponseEntity.ok(Response.builder()
                .data(Map.of("id", categoryId))
                .message("Category created successfully")
                .status(HttpStatus.CREATED)
                .build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(categoryDTOs);
    }
}
