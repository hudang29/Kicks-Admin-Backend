package com.poly.admin.repository;

import com.poly.admin.model.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderAuditLogRepo extends JpaRepository<OrderLog, Integer> {
    List<OrderLog> findByType(String type);
    List<OrderLog> findByCreatedBy(String createdBy);
    List<OrderLog> findByTypeAndCreatedBy(String type, String createdBy);
}
