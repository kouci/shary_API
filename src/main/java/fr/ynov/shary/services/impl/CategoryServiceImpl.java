package fr.ynov.shary.services.impl;


import fr.ynov.shary.models.Category;

import fr.ynov.shary.repository.CategoryRepository;

import fr.ynov.shary.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public long createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return savedCategory.getCategory_id();
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public long updateCategory(Category category) {
        Category updatedUser = categoryRepository.save(category);
        return updatedUser.getCategory_id();
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

}
