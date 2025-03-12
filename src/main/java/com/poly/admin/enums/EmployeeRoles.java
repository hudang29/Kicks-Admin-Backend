package com.poly.admin.enums;

public enum EmployeeRoles {
    STAFF,
    MANAGER,
    ADMIN;

    public static EmployeeRoles fromString(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        return EmployeeRoles.valueOf(role.toUpperCase());
    }
}

