package com.poly.admin.controller;

import com.poly.admin.dto.Account;
import com.poly.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        boolean isAuthenticated = loginService.checkLogin(account.getEmail(), account.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong email or password");
        }
    }
}
