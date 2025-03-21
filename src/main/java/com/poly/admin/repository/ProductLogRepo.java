package com.poly.admin.repository;

import com.poly.admin.model.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLogRepo extends JpaRepository<ProductLog, Integer> {

    List<ProductLog> findByType(String type);
    List<ProductLog> findByEditBy(String createdBy);
    List<ProductLog> findByTypeAndEditBy(String type, String createdBy);
}
