package com.poly.admin.repository;

import com.poly.admin.model.ShoesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoesCategoryRepo extends JpaRepository<ShoesCategory, Integer> {
    List<ShoesCategory> findByGenderCategoryID(Integer id);
}
