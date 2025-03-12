package com.poly.admin.service;

import com.poly.admin.dto.AuthRequest;
import com.poly.admin.dto.AuthResponse;
import com.poly.admin.enums.EmployeeRoles;
import com.poly.admin.model.Employee;
import com.poly.admin.repository.EmployeeRepo;
import com.poly.admin.repository.PasswordRepo;
import com.poly.admin.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private PasswordRepo passwordRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private HashedPassword hashedPassword;

    public AuthResponse checkLogin(AuthRequest authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        Optional<Employee> employee = employeeRepo.findByEmail(email);

        if (employee.isPresent()) {
            String hashedPasswordDb = passwordRepo.findPasswordByEmployee_Id(employee.get().getId());
            if (hashedPasswordDb != null && hashedPassword.checkPassword(password, hashedPasswordDb)) {

                return new AuthResponse(employee.get().getId(),
                        employee.get().getRole().name(),
                        true);
            }
        }

        return new AuthResponse(null, "", false);
    }

}
