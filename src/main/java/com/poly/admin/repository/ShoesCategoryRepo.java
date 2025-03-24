package com.poly.admin.repository;

import com.poly.admin.model.ShoesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoesCategoryRepo extends JpaRepository<ShoesCategory, Integer> {
    List<ShoesCategory> findByGenderCategoryId(Integer id);

    List<ShoesCategory> findAll();

    Optional<ShoesCategory> findById(Integer id);

    @Override
    <S extends ShoesCategory> S save(S entity);
}
