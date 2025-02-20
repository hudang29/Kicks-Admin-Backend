package com.poly.admin.service;

import com.poly.admin.model.ParentCategory;
import com.poly.admin.repository.CategoryRepo;
import com.poly.admin.repository.ParentCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ParentCategoryRepo parentCategoryRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public List<ParentCategory> getAllParentCategories() {
        return parentCategoryRepo.findAll();
    }
}
