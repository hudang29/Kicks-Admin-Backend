package com.poly.admin.controller;

import com.poly.admin.dto.ShoesCategoryDTO;
import com.poly.admin.model.GenderCategory;
import com.poly.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/gender-category")
    public List<GenderCategory> getAllGenderCategory() {
        return categoryService.getAllGenderCategory();
    }

    @GetMapping("/api/gender-category/{id}")
    public GenderCategory getGenderCategoryById(@PathVariable Integer id) {
        return categoryService.getGenderCategoryById(id);
    }

    @GetMapping("/api/shoes-category/{id}")
    public Optional<ShoesCategoryDTO> getShoeCategoryById(@PathVariable("id") Integer id) {
        return categoryService.getShoesCategoryById(id);
    }

    @GetMapping("/api/shoes-categories/{id}")
    public List<ShoesCategoryDTO> getAllShoeCategoryByGenderCategoryId(@PathVariable("id") Integer id) {
        return categoryService.getAllShoesCategoryByGenderCategoryId(id);
    }
}
