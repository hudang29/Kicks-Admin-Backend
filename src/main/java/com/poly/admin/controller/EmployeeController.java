package com.poly.admin.controller;

import com.poly.admin.enums.EmployeeRoles;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.model.Employee;
import com.poly.admin.service.EmployeeService;
import com.poly.admin.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manager")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/api/roles")
    public ResponseEntity<List<String>> getRoles() {
        List<String> roles = Arrays.stream(EmployeeRoles.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/api/show-employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có nhân viên nào
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/api/show-employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy nhân viên
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/api/check-exists-password/{id}")
    public ResponseEntity<Boolean> existsPassword(@PathVariable Integer id) {
        boolean exists = employeeService.getPasswordByEmployeeId(id);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/api/update-employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.createOrUpdateEmployee(employee);
            return ResponseEntity.ok(updatedEmployee); // Return success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the employee: " + e.getMessage());
        }
    }


    @PutMapping("/api/change-status-employee")
    public ResponseEntity<?> changeStatus(@RequestBody Employee employee) {
        try {
            Employee employeeStatus = employeeService.changeStatus(employee);
            return ResponseEntity.ok(employeeStatus); // Return success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while changing employee status: " + e.getMessage());
        }
    }

    @PostMapping("/api/create-employee")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            Employee staff = new Employee(savedEmployee.getName(),
                    savedEmployee.getEmail(),
                    savedEmployee.getPhone(),
                    savedEmployee.getRole(),
                    savedEmployee.getAddress(),
                    savedEmployee.getCity(),
                    savedEmployee.getDistrict(),
                    savedEmployee.getWard());
            return ResponseEntity.ok(staff); // Return success response
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating a new employee: " + e.getMessage());
        }
    }

    @PostMapping("/api/create-password")
    public ResponseEntity<?> createPassword(@RequestBody Employee employee) {
        String email = employee.getEmail();

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Email cannot be empty.");
        }

        try {
            employeeService.createAccountForEmployee(email);
            return ResponseEntity.ok("Password creation successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the password: " + e.getMessage());
        }
    }

}
