package com.poly.admin.repository;

import com.poly.admin.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p.name FROM Product p WHERE p.id = :id")
    String findNameById(@Param("id") Integer id);

    List<Product> findAllByOrderByCreateAtDesc();

    @Override
    Page<Product> findAll(Pageable pageable);
}
