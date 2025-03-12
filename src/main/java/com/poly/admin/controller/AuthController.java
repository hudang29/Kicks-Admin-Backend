package com.poly.admin.controller;

import com.poly.admin.dto.AuthRequest;
import com.poly.admin.dto.AuthResponse;
import com.poly.admin.service.AuthService;
import com.poly.admin.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/api/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest account, HttpServletResponse response) {
        AuthResponse isAuthenticated = authService.checkLogin(account);
        if(isAuthenticated.isSuccess()) {
            String token = jwtUtil.generateToken(account.getEmail(),isAuthenticated.getRole());
            // Tạo cookie HttpOnly
            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);  // Bật trên HTTPS true
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(86400);
            //jwtCookie.setAttribute("SameSite", "None");
            response.addCookie(jwtCookie);

            //Message = "Have token into cookie: "+ isAuthenticated.getToken();
        }
        return ResponseEntity.ok(authService.checkLogin(account));
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        // Xóa cookie "jwt" bằng cách đặt maxAge = 0
        Cookie clearCookie = new Cookie("jwt", "");
        clearCookie.setHttpOnly(true);
        clearCookie.setSecure(false);
        clearCookie.setPath("/");
        clearCookie.setMaxAge(0); // Xóa cookie ngay lập tức
        response.addCookie(clearCookie);

        return ResponseEntity.ok("Logged out successfully");
    }

}
