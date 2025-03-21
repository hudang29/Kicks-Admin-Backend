package com.poly.admin.controller;

import com.poly.admin.model.DiscountLog;
import com.poly.admin.model.OrderLog;
import com.poly.admin.model.ProductLog;
import com.poly.admin.repository.DiscountLogRepo;
import com.poly.admin.repository.OrderLogRepo;
import com.poly.admin.repository.ProductLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/admin/api/log")
public class LogController {
    @Autowired
    private ProductLogRepo productLogRepo;
    @Autowired
    private OrderLogRepo orderLogRepo;
    @Autowired
    private DiscountLogRepo discountLogRepo;


    @PostMapping("/product/save")
    public ResponseEntity<ProductLog> saveProductLog(@RequestBody ProductLog log) {
        log.setTimeStamp(Instant.now()); // Cập nhật thời gian hiện tại
        return ResponseEntity.ok(productLogRepo.save(log));
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<ProductLog>> getAllProductLogs() {
        return ResponseEntity.ok(productLogRepo.findAll());
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductLog>> searchProductLogs(@RequestParam(required = false) String type,
                                                              @RequestParam(required = false) String createdBy) {
        if (type != null && createdBy != null) {
            return ResponseEntity.ok(productLogRepo.findByTypeAndEditBy(type, createdBy));
        } else if (type != null) {
            return ResponseEntity.ok(productLogRepo.findByType(type));
        } else if (createdBy != null) {
            return ResponseEntity.ok(productLogRepo.findByEditBy(createdBy));
        }
        return ResponseEntity.ok(productLogRepo.findAll());
    }

    @PostMapping("/orders/save")
    public ResponseEntity<OrderLog> saveOrderLog(@RequestBody OrderLog log) {
        log.setTimeStamp(Instant.now()); // Cập nhật thời gian hiện tại
        return ResponseEntity.ok(orderLogRepo.save(log));
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List<OrderLog>> getAllOrderLogs() {
        return ResponseEntity.ok(orderLogRepo.findAll());
    }

    @GetMapping("/orders/search")
    public ResponseEntity<List<OrderLog>> searchOrderLogs(@RequestParam(required = false) String type,
                                                          @RequestParam(required = false) String createdBy) {
        if (type != null && createdBy != null) {
            return ResponseEntity.ok(orderLogRepo.findByTypeAndEditBy(type, createdBy));
        } else if (type != null) {
            return ResponseEntity.ok(orderLogRepo.findByType(type));
        } else if (createdBy != null) {
            return ResponseEntity.ok(orderLogRepo.findByEditBy(createdBy));
        }
        return ResponseEntity.ok(orderLogRepo.findAll());
    }

    @PostMapping("/discount/save")
    public ResponseEntity<DiscountLog> saveDiscountLog(@RequestBody DiscountLog log) {
        log.setTimeStamp(Instant.now()); // Cập nhật thời gian hiện tại
        return ResponseEntity.ok(discountLogRepo.save(log));
    }

    @GetMapping("/discount/all")
    public ResponseEntity<List<DiscountLog>> getAllDiscountLogs() {
        return ResponseEntity.ok(discountLogRepo.findAll());
    }

    @GetMapping("/discount/search")
    public ResponseEntity<List<DiscountLog>> searchDiscountLogs(@RequestParam(required = false) String type,
                                                                @RequestParam(required = false) String createdBy) {
        if (type != null && createdBy != null) {
            return ResponseEntity.ok(discountLogRepo.findByTypeAndEditBy(type, createdBy));
        } else if (type != null) {
            return ResponseEntity.ok(discountLogRepo.findByType(type));
        } else if (createdBy != null) {
            return ResponseEntity.ok(discountLogRepo.findByEditBy(createdBy));
        }
        return ResponseEntity.ok(discountLogRepo.findAll());
    }
}
