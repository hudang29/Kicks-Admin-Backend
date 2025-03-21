package com.poly.admin.repository;

import com.poly.admin.model.DiscountLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountLogRepo extends JpaRepository<DiscountLog, Integer> {

    List<DiscountLog> findByType(String type);
    List<DiscountLog> findByEditBy(String createdBy);
    List<DiscountLog> findByTypeAndEditBy(String type, String createdBy);
}
