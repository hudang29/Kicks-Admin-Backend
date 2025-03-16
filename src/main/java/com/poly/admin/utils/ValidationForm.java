package com.poly.admin.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidationForm {

    public boolean isValidPassword(String password) {
        // Độ dài tối thiểu 8 ký tự
        if (password.length() < 8) {
            return false;
        }

        // Phải chứa ít nhất một chữ cái viết hoa
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Phải chứa ít nhất một chữ số
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return false;
        }

        return true; // Mật khẩu hợp lệ
    }

}
