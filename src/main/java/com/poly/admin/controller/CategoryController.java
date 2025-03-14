package com.poly.admin.controller;

import com.poly.admin.dto.ShoesCategoryDTO;
import com.poly.admin.model.GenderCategory;
import com.poly.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/gender-category")
    public ResponseEntity<List<GenderCategory>> getAllGenderCategory() {
        List<GenderCategory> categories = categoryService.getAllGenderCategory();
        return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
    }

    @GetMapping("/api/gender-category/{id}")
    public ResponseEntity<GenderCategory> getGenderCategoryById(@PathVariable Integer id) {
        GenderCategory category = categoryService.getGenderCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/shoes-category/{id}")
    public ResponseEntity<ShoesCategoryDTO> getShoeCategoryById(@PathVariable Integer id) {
        return categoryService.getShoesCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/shoes-categories/{id}")
    public ResponseEntity<List<ShoesCategoryDTO>> getAllShoeCategoryByGenderCategoryId(@PathVariable Integer id) {
        List<ShoesCategoryDTO> categories = categoryService.getAllShoesCategoryByGenderCategoryId(id);
        return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
    }

}
