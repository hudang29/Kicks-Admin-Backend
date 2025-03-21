package com.poly.admin.controller;

import com.poly.admin.model.DiscountLog;
import com.poly.admin.repository.DiscountAuditLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/admin/api/discount-audit-logs")
@CrossOrigin(origins = "*") // Cho phép React truy cập API
public class DiscountAuditLogController {
    @Autowired
    private DiscountAuditLogRepo discountAuditLogRepo;

    @PostMapping("/save")
    public ResponseEntity<DiscountLog> saveLog(@RequestBody DiscountLog log) {
        log.setTimeStamp(Instant.now()); // Cập nhật thời gian hiện tại
        return ResponseEntity.ok(discountAuditLogRepo.save(log));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiscountLog>> getAllLogs() {
        return ResponseEntity.ok(discountAuditLogRepo.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<DiscountLog>> searchLogs(@RequestParam(required = false) String type,
                                                          @RequestParam(required = false) String createdBy) {
        if (type != null && createdBy != null) {
            return ResponseEntity.ok(discountAuditLogRepo.findByTypeAndCreatedBy(type, createdBy));
        } else if (type != null) {
            return ResponseEntity.ok(discountAuditLogRepo.findByType(type));
        } else if (createdBy != null) {
            return ResponseEntity.ok(discountAuditLogRepo.findByCreatedBy(createdBy));
        }
        return ResponseEntity.ok(discountAuditLogRepo.findAll());
    }


}

