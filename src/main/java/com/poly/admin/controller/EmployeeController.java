package com.poly.admin.controller;

import com.poly.admin.dto.EmployeeDTO;
import com.poly.admin.dto.OrderDTO;
import com.poly.admin.model.Employee;
import com.poly.admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/show-employee")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/show-employee/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/api/update-employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee Employee) {
        try {
            employeeService.createOrUpdateEmployee(Employee);
            return ResponseEntity.ok("Cập nhật thành công!"); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi cập nhật: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }

    @PutMapping("/api/change-status-employee")
    public ResponseEntity<?> changeStatus(@RequestBody Employee Employee) {
        try {
            employeeService.changeStatus(Employee);
            return ResponseEntity.ok("thay đổi trạng thái thành công"); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }

    @PostMapping("/api/create-employee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee Employee) {
        try {
            employeeService.createOrUpdateEmployee(Employee);
            return ResponseEntity.ok("Tạo thành công!"); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi tạo: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }
}
