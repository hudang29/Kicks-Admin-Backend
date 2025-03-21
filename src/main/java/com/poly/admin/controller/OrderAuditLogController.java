package com.poly.admin.controller;

import com.poly.admin.model.OrderLog;
import com.poly.admin.repository.OrderAuditLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/admin/api/order-audit-logs")
@CrossOrigin(origins = "*") // Cho phép React truy cập API
public class OrderAuditLogController {
    @Autowired
    private OrderAuditLogRepo orderAuditLogRepository;

    @PostMapping("/save")
    public ResponseEntity<OrderLog> saveLog(@RequestBody OrderLog log) {
        log.setTimeStamp(Instant.now()); // Cập nhật thời gian hiện tại
        return ResponseEntity.ok(orderAuditLogRepository.save(log));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderLog>> getAllLogs() {
        return ResponseEntity.ok(orderAuditLogRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderLog>> searchLogs(@RequestParam(required = false) String type,
                                                          @RequestParam(required = false) String createdBy) {
        if (type != null && createdBy != null) {
            return ResponseEntity.ok(orderAuditLogRepository.findByTypeAndCreatedBy(type, createdBy));
        } else if (type != null) {
            return ResponseEntity.ok(orderAuditLogRepository.findByType(type));
        } else if (createdBy != null) {
            return ResponseEntity.ok(orderAuditLogRepository.findByCreatedBy(createdBy));
        }
        return ResponseEntity.ok(orderAuditLogRepository.findAll());
    }
}
