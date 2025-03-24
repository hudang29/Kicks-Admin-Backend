package com.poly.admin.service;

import com.poly.admin.dto.ShoesCategoryDTO;
import com.poly.admin.model.GenderCategory;
import com.poly.admin.model.ShoesCategory;
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

    public ShoesCategoryDTO updateShoesCategory(ShoesCategoryDTO dto) {
        if (!shoesCategoryRepo.existsById(dto.getId())) {
            throw new IllegalArgumentException("Shoes Category Not Found");
        }
        GenderCategory genderCategory = genderCategoryRepo.findById(dto.getGenderCategoryID()).orElseThrow(
                () -> new IllegalArgumentException("Gender Category Not Found")
        );
        if (dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is Empty");
        }
        ShoesCategory shoesCategory = new ShoesCategory();
        shoesCategory.setId(dto.getId());
        shoesCategory.setGenderCategory(genderCategory);
        shoesCategory.setName(dto.getName());


        ShoesCategory response = shoesCategoryRepo.save(shoesCategory);
        return new ShoesCategoryDTO(response.getId(),
                response.getGenderCategory().getId(),
                response.getName());
    }

    public ShoesCategoryDTO addShoesCategory(ShoesCategoryDTO dto) {
        GenderCategory genderCategory = genderCategoryRepo.findById(dto.getGenderCategoryID()).orElseThrow(
                () -> new IllegalArgumentException("Gender Category Not Found")
        );
        if (dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is Empty");
        }
        ShoesCategory shoesCategory = new ShoesCategory();
        shoesCategory.setGenderCategory(genderCategory);
        shoesCategory.setName(dto.getName());

        ShoesCategory response = shoesCategoryRepo.save(shoesCategory);
        return new ShoesCategoryDTO(response.getId(),
                response.getGenderCategory().getId(),
                response.getName());
    }

    public GenderCategory addGenderCategory(GenderCategory gender) {

        if (gender.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is Empty");
        }
        return genderCategoryRepo.save(gender);
    }

    public GenderCategory updateGenderCategory(GenderCategory gender) {
        if (!genderCategoryRepo.existsById(gender.getId())) {
            throw new IllegalArgumentException("Gender Category Not Found");
        }
        if (gender.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is Empty");
        }
        return genderCategoryRepo.save(gender);
    }

}