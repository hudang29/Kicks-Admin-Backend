package com.poly.admin.utils;

import io.micrometer.common.util.StringUtils;
import java.math.BigDecimal;

public class ValidationForm {

    public static boolean isValidPassword(String password) {
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

    // Kiểm tra tên (Chỉ chứa chữ cái, khoảng trắng, gạch ngang, từ 2-50 ký tự)
    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        String regex = "^[A-Za-zÀ-Ỹà-ỹ\\s\\-]{2,80}$";
        return name.matches(regex);
    }

    // Kiểm tra email hợp lệ
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    // Kiểm tra số điện thoại (Chỉ 10 số, bắt đầu bằng 0)
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        String regex = "^0[1-9]\\d{8}$"; // Bắt đầu bằng 0, không có số 0 thừa
        return phone.matches(regex);
    }

    // Kiểm tra giá sản phẩm (Chỉ số nguyên dương, không bắt đầu bằng 0)
    public static boolean isValidPrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

    // Kiểm tra số lượng tồn kho (Chỉ số nguyên dương, không bắt đầu bằng 0)
    public static boolean isValidStock(Integer stock) {
        return stock == null || stock <= 0;
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

}
