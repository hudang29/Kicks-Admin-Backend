package com.poly.admin.service;

import com.poly.admin.model.Employee;
import com.poly.admin.model.EmployeePassword;
import com.poly.admin.repository.EmployeeRepo;
import com.poly.admin.repository.PasswordRepo;
import com.poly.admin.utils.HashedPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private PasswordRepo passwordRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private HashedPassword hashedPassword;

    public boolean checkLogin(String email, String password) {
        Optional<Employee> employee = employeeRepo.findByEmail(email);

        if (employee.isPresent()) {
            String hashedPasswordDb = passwordRepo.findPasswordByEmployee_Id(employee.get().getId());

            if (hashedPasswordDb != null) {
                return hashedPassword.checkPassword(password, hashedPasswordDb);
            }
        }
        return false;
    }
}
