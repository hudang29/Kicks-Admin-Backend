package com.poly.admin.service;

import com.poly.admin.dto.EmployeeDTO;
import com.poly.admin.model.Employee;
import com.poly.admin.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll()
                .stream()
                .map(employee -> new EmployeeDTO(
                        employee.getId(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getRole(),
                        employee.getStatus()
                )).toList();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public EmployeeDTO getEmployeeById(Integer id) {

        return employeeRepo.findById(id).map(employee -> new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getAddress(),
                employee.getRole(),
                employee.getStatus()
        )).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void createOrUpdateEmployee(Employee employeeData) {
        Integer id = employeeData.getId();
        Employee employee;
        if (employeeRepo.existsById(id)) {
            employee = employeeRepo.findById(id).orElseThrow(
                    () -> new RuntimeException("Employee not found"));
        } else {
            employee = new Employee();
            employee.setPassword(employeeData.getPassword());

        }

        employee.setName(employeeData.getName());
        employee.setEmail(employeeData.getEmail());
        employee.setPhone(employeeData.getPhone());
        employee.setAddress(employeeData.getAddress());
        employee.setRole(employeeData.getRole());
        employee.setStatus(employeeData.getStatus());

        employeeRepo.save(employee);
    }

    public void changeStatus(Employee employeeData) {
        Integer id = employeeData.getId();
        Employee employee;
        if (employeeRepo.existsById(id)) {
            employee = employeeRepo.findById(id).orElseThrow(
                    () -> new RuntimeException("Employee not found"));
        } else {
            return;
        }
        employee.setStatus(employeeData.getStatus());
        employeeRepo.save(employee);
    }

}
