package com.poly.admin.controller;

import com.poly.admin.dto.ShoesCategoryDTO;
import com.poly.admin.model.GenderCategory;
import com.poly.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/gender-category")
    public ResponseEntity<List<GenderCategory>> getAllGenderCategory() {
        List<GenderCategory> categories = categoryService.getAllGenderCategory();
        return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
    }

    @GetMapping("/gender-category/{id}")
    public ResponseEntity<GenderCategory> getGenderCategoryById(@PathVariable Integer id) {
        GenderCategory category = categoryService.getGenderCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @GetMapping("/shoes-category/{id}")
    public ResponseEntity<ShoesCategoryDTO> getShoeCategoryById(@PathVariable Integer id) {
        return categoryService.getShoesCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/shoes-categories/{id}")
    public ResponseEntity<List<ShoesCategoryDTO>> getAllShoeCategoryByGenderCategoryId(@PathVariable Integer id) {
        List<ShoesCategoryDTO> categories = categoryService.getAllShoesCategoryByGenderCategoryId(id);
        return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
    }

    @PutMapping("/shoes-category/update")
    public ResponseEntity<?> updateShoesCategory(@RequestBody ShoesCategoryDTO shoesCategoryDTO) {
        try {
            ShoesCategoryDTO response = categoryService.updateShoesCategory(shoesCategoryDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/gender-category/update")
    public ResponseEntity<?> updateGenderCategory(@RequestBody GenderCategory gender) {
        try {
            GenderCategory response = categoryService.updateGenderCategory(gender);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/gender-category/add")
    public ResponseEntity<?> addGenderCategory(@RequestBody GenderCategory gender) {
        try {
            GenderCategory response = categoryService.addGenderCategory(gender);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/shoes-category/add")
    public ResponseEntity<?> addShoesCategory(@RequestBody ShoesCategoryDTO dto) {
        try {
            ShoesCategoryDTO response = categoryService.addShoesCategory(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
