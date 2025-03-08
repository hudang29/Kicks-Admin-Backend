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
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/show-employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/api/update-employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee updateEmployee = employeeService.createOrUpdateEmployee(employee);
            return ResponseEntity.ok(updateEmployee); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi cập nhật: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }

    @PutMapping("/api/change-status-employee")
    public ResponseEntity<?> changeStatus(@RequestBody Employee Employee) {
        try {
            Employee employeeStatus = employeeService.changeStatus(Employee);
            return ResponseEntity.ok(employeeStatus); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }

    @PostMapping("/api/create-employee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee Employee) {
        try {
            Employee employee = employeeService.saveEmployee(Employee);
            Employee staff = new Employee(employee.getName(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getRole(),
                    employee.getAddress(),
                    employee.getCity(),
                    employee.getDistrict(),
                    employee.getWard());
            return ResponseEntity.ok(staff); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errors: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }

    @PostMapping("/api/create-password")
    public ResponseEntity<?> createPassword(@RequestBody Employee employee) {
        String email = employee.getEmail();
        try {
            employeeService.createAccountForEmployee(email);
            return ResponseEntity.ok("Successful");
            // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errors: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }
}
