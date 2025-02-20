package com.poly.admin.controller;

import com.poly.admin.dto.EmployeeDTO;
import com.poly.admin.dto.OrderDTO;
import com.poly.admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/show-employee")
    public List<EmployeeDTO> getAllOrders() {
        return employeeService.getAllEmployees();
    }
}
