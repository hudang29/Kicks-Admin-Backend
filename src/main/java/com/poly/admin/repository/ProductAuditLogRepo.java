package com.poly.admin.repository;

import com.poly.admin.model.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAuditLogRepo extends JpaRepository<ProductLog, Integer> {
    List<ProductLog> findByType(String type);
    List<ProductLog> findByCreatedBy(String createdBy);
    List<ProductLog> findByTypeAndCreatedBy(String type, String createdBy);
}
