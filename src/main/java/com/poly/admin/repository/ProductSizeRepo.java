package com.poly.admin.repository;

import com.poly.admin.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSizeRepo extends JpaRepository<ProductSize, Integer> {

    List<ProductSize> findByProductDetail_Id(Integer id);

    @Override
    List<ProductSize> findAll();

    @Override
    Optional<ProductSize> findById(Integer id);
}
