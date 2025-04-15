package com.poly.admin.controller;

import com.poly.admin.dto.AuthRequest;
import com.poly.admin.dto.AuthResponse;
import com.poly.admin.service.AuthService;
import com.poly.admin.service.EmployeeService;
import com.poly.admin.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest account, HttpServletResponse response) {
        AuthResponse authResponse = authService.checkLogin(account);

        if (authResponse.isSuccess()) {
            String token = jwtUtil.generateToken(account.getEmail(), authResponse.getRole());

            // Create HttpOnly cookie for JWT
            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);  // Set to true if using HTTPS
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(86400);  // Expire in 1 day

            response.addCookie(jwtCookie);
        }

        return ResponseEntity.ok(authResponse);
    }


    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // Clear the "jwt" cookie by setting maxAge = 0
        Cookie clearCookie = new Cookie("jwt", "");
        clearCookie.setHttpOnly(true);
        clearCookie.setSecure(false);  // Set to true if using HTTPS
        clearCookie.setPath("/");
        clearCookie.setMaxAge(0); // Delete the cookie immediately
        response.addCookie(clearCookie);

        return ResponseEntity.ok("You have been logged out successfully.");
    }

    @PutMapping("/api/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody String email) {
        try {
            employeeService.forgotPassword(email);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
