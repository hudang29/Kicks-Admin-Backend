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

    @GetMapping("/api/gender-categories")
    public List<GenderCategory> getAllParentCategories() {
        return categoryService.getAllGenderCategory();
    }

    @GetMapping("/api/shoe-category/{id}")
    public Optional<ShoesCategoryDTO> getShoeCategoryById(@PathVariable("id") Integer id) {
        return categoryService.getShoesCategoryById(id);
    }
}
