package com.poly.admin.repository;

import com.poly.admin.model.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLogRepo extends JpaRepository<OrderLog, Integer> {

    List<OrderLog> findByType(String type);
    List<OrderLog> findByEditBy(String createdBy);
    List<OrderLog> findByTypeAndEditBy(String type, String createdBy);
}
