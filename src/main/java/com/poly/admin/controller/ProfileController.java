package com.poly.admin.controller;

import com.poly.admin.dto.ChangePassword;
import com.poly.admin.model.Employee;
import com.poly.admin.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff/api")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/show-profile/{employeeId}")
    public ResponseEntity<?> getProfile(@PathVariable int employeeId) {
        Employee employee = profileService.getProfile(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy nhân viên
        }
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/profile")
    public ResponseEntity<?> updateProfile(@RequestBody Employee employee) {
        Employee employeeUpdated = profileService.updateProfile(employee);
        if (employeeUpdated == null) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy nhân viên
        }
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(@RequestBody ChangePassword changePassword) {
        boolean isCheck = profileService.changePassword(changePassword);
        if (!isCheck) {
            return ResponseEntity.ok(false); // Trả về 404 nếu không tìm thấy nhân viên
        }
        return ResponseEntity.ok(true);
    }
}
