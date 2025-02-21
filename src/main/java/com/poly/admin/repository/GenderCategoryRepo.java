package com.poly.admin.repository;

import com.poly.admin.model.GenderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderCategoryRepo extends JpaRepository<GenderCategory, Integer> {
    @Override
    List<GenderCategory> findAll();
}
