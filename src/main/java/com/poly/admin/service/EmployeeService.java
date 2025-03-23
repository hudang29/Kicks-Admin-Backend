package com.poly.admin.service;

import com.poly.admin.model.Employee;
import com.poly.admin.model.EmployeePassword;
import com.poly.admin.repository.EmployeeRepo;
import com.poly.admin.repository.PasswordRepo;
import com.poly.admin.utils.Generator;
import com.poly.admin.utils.HashedPassword;
import com.poly.admin.utils.ValidationForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordRepo passwordRepo;
    @Autowired
    private HashedPassword hashedPassword;
    @Autowired
    private MailService mailService;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789@#$%^&*";
    private static final int PASSWORD_LENGTH = 10;


    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee getEmployeeById(Integer id) {

        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee createOrUpdateEmployee(Employee employeeData) {
        Integer id = employeeData.getId();
        String name = employeeData.getName();
        String email = employeeData.getEmail();
        String phone = employeeData.getPhone();

        if (!ValidationForm.isValidName(name) ||
                !ValidationForm.isValidEmail(email) ||
                !ValidationForm.isValidPhoneNumber(phone)) {
            throw new IllegalArgumentException("Invalid input");
        }

        Employee employee;
        if (employeeRepo.existsById(id)) {
            employee = employeeRepo.findById(id).orElseThrow(
                    () -> new NullPointerException("Employee not found"));
        } else {
            employee = new Employee();
            employee.setStatus(true);
            employee.setCreateAt(Instant.now());
        }


        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setAddress(employeeData.getAddress());
        employee.setRole(employeeData.getRole());
        employee.setCity(employeeData.getCity());
        employee.setDistrict(employeeData.getDistrict());
        employee.setWard(employeeData.getWard());

        return employeeRepo.save(employee);
    }

    public Employee changeStatus(Employee employeeData) {
        Integer id = employeeData.getId();
        Employee employee;
        if (employeeRepo.existsById(id)) {
            employee = employeeRepo.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Employee not found"));
        } else {
            return null;
        }
        employee.setStatus(employeeData.getStatus());
        return employeeRepo.save(employee);
    }

    public boolean getPasswordByEmployeeId(Integer id) {
        return passwordRepo.existsByEmployee_Id(id);
    }


    public void createAccountForEmployee(String email) {
        Optional<Employee> optionalEmployee = employeeRepo.findByEmail(email);
        if (optionalEmployee.isEmpty()) {
            return;
        }
        try {
            Employee employee = optionalEmployee.get();
            String rawPassword = Generator.generateRandomPassword(PASSWORD_LENGTH, CHARACTERS);
            // Mã hóa mật khẩu bằng BCrypt
            String encodedPassword = hashedPassword.hashPassword(rawPassword);
            EmployeePassword password = new EmployeePassword();
            password.setHashedPassword(encodedPassword);
            password.setEmployee(employee);
            password.setCreateAt(Instant.now());
            passwordRepo.save(password);

            boolean emailSent = mailService.sendPasswordToEmployee(email, rawPassword);
            if (!emailSent) {
                throw new RuntimeException("Gửi email thất bại, hủy tạo tài khoản.");
            }
        } catch (Exception e) {
            throw new EntityNotFoundException("Employee not found");
        }

    }
}
