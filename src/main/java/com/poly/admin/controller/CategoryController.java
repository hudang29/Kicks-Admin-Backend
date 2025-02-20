package com.poly.admin.controller;

import com.poly.admin.model.ParentCategory;
import com.poly.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/category")
    public List<ParentCategory> category() {
        List<ParentCategory> parentCategories = categoryService.getAllParentCategories();
        for (ParentCategory parentCategory : parentCategories) {
            System.out.println(parentCategory.getId());
            System.out.println(parentCategory.getName());
        }
        return parentCategories;
    }
}
