package com.poly.admin.repository;

import com.poly.admin.model.DiscountLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountAuditLogRepo extends JpaRepository<DiscountLog, Integer> {
    List<DiscountLog> findByType(String type);
    List<DiscountLog> findByCreatedBy(String createdBy);
    List<DiscountLog> findByTypeAndCreatedBy(String type, String createdBy);
}
