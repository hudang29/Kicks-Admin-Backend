package com.poly.admin.controller;

import com.poly.admin.model.ProductLog;
import com.poly.admin.repository.ProductAuditLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/admin/api/product-audit-logs")
@CrossOrigin(origins = "*") // Cho phép React truy cập API
public class ProductAuditLogController {
    @Autowired
    private ProductAuditLogRepo productAuditLogRepo;

    @PostMapping("/save")
    public ResponseEntity<ProductLog> saveLog(@RequestBody ProductLog log) {
        log.setTimeStamp(Instant.now()); // Cập nhật thời gian hiện tại
        return ResponseEntity.ok(productAuditLogRepo.save(log));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductLog>> getAllLogs() {
        return ResponseEntity.ok(productAuditLogRepo.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductLog>> searchLogs(@RequestParam(required = false) String type,
                                                          @RequestParam(required = false) String createdBy) {
        if (type != null && createdBy != null) {
            return ResponseEntity.ok(productAuditLogRepo.findByTypeAndCreatedBy(type, createdBy));
        } else if (type != null) {
            return ResponseEntity.ok(productAuditLogRepo.findByType(type));
        } else if (createdBy != null) {
            return ResponseEntity.ok(productAuditLogRepo.findByCreatedBy(createdBy));
        }
        return ResponseEntity.ok(productAuditLogRepo.findAll());
    }
}


