package com.poly.admin.repository;

import com.poly.admin.model.ParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParentCategoryRepo extends JpaRepository<ParentCategory, Integer> {
    @Override
    List<ParentCategory> findAll();
}
