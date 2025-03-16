package com.poly.admin.service;

import com.poly.admin.model.Employee;
import com.poly.admin.model.EmployeePassword;
import com.poly.admin.repository.EmployeeRepo;
import com.poly.admin.repository.PasswordRepo;
import com.poly.admin.utils.HashedPassword;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
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
        Employee employee;
        if (employeeRepo.existsById(id)) {
            employee = employeeRepo.findById(id).orElseThrow(
                    () -> new NullPointerException("Employee not found"));
        } else {
            employee = new Employee();
            employee.setStatus(true);
            employee.setCreateAt(Instant.now());
        }

        employee.setName(employeeData.getName());
        employee.setEmail(employeeData.getEmail());
        employee.setPhone(employeeData.getPhone());
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

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    public void createAccountForEmployee(String email) {
        Optional<Employee> optionalEmployee = employeeRepo.findByEmail(email);
        if (optionalEmployee.isEmpty()) {
            return;
        }
        try {
            Employee employee = optionalEmployee.get();
            String rawPassword = generateRandomPassword();
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
            e.printStackTrace();
        }

    }

    public String getRoleByEmail(String email) {
        boolean check = employeeRepo.existsByEmail(email);
        if (!check) {
            return "";
        }
        return employeeRepo.findRoleByEmail(email);
    }


}
