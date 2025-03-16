package com.poly.admin.service;

import com.poly.admin.dto.ChangePassword;
import com.poly.admin.model.Employee;
import com.poly.admin.model.EmployeePassword;
import com.poly.admin.repository.EmployeeRepo;
import com.poly.admin.repository.PasswordRepo;
import com.poly.admin.utils.HashedPassword;
import com.poly.admin.utils.ValidationForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordRepo passwordRepo;
    @Autowired
    private HashedPassword hashedPassword;
    @Autowired
    private ValidationForm validationForm;

    public Employee getProfile(Integer employeeId) {
        return employeeRepo.findById(employeeId).orElseThrow(EntityNotFoundException::new);
    }

    public Employee updateProfile(Employee employee) {
        return employeeRepo.findById(employee.getId())
                .map(existingEmployee -> {
                    existingEmployee.setName(employee.getName());
                    existingEmployee.setEmail(employee.getEmail());
                    existingEmployee.setPhone(employee.getPhone());
                    existingEmployee.setAddress(employee.getAddress());
                    existingEmployee.setCity(employee.getCity());
                    existingEmployee.setDistrict(employee.getDistrict());
                    existingEmployee.setWard(employee.getWard());
                    return employeeRepo.save(existingEmployee);
                })
                .orElse(null);
    }

    public boolean changePassword(ChangePassword changePassword) {
        if (changePassword.getOldPassword().equals(changePassword.getNewPassword())) {
            return false;
        }
        if (!validationForm.isValidPassword(changePassword.getNewPassword())) {
            return false;
        }
        return passwordRepo.findByEmployee_Id(changePassword.getEmployeeId())
                .filter(password -> hashedPassword.checkPassword(
                        changePassword.getOldPassword(), password.getHashedPassword()))
                .map(password -> {
                    password.setHashedPassword(hashedPassword.hashPassword(changePassword.getNewPassword()));
                    passwordRepo.save(password);
                    return true;
                })
                .orElse(false);
    }


}
