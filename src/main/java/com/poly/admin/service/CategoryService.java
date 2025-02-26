package com.poly.admin.service;

import com.poly.admin.dto.ShoesCategoryDTO;
import com.poly.admin.model.GenderCategory;
import com.poly.admin.repository.ShoesCategoryRepo;
import com.poly.admin.repository.GenderCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private GenderCategoryRepo genderCategoryRepo;
    @Autowired
    private ShoesCategoryRepo shoesCategoryRepo;

    public List<GenderCategory> getAllGenderCategory() {

        return genderCategoryRepo.findAll();
    }

    public List<ShoesCategoryDTO> getAllShoesCategoryByGenderCategoryId(int id) {
        return shoesCategoryRepo.findByGenderCategoryId(id)
                .stream()
                .map(category -> new ShoesCategoryDTO(
                        category.getId(),
                        category.getGenderCategory().getId(),
                        category.getName()
                )).toList();
    }

    public Optional<ShoesCategoryDTO> getShoesCategoryById(int id) {
        return shoesCategoryRepo.findById(id).map(category -> new ShoesCategoryDTO(
                category.getId(),
                category.getGenderCategory().getId(),
                category.getName()
        ));
    }

    public GenderCategory getGenderCategoryById(int id) {

        return genderCategoryRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Gender Category Not Found")
        );
    }
}