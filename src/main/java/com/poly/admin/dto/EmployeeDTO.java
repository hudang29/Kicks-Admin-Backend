package com.poly.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    private Boolean status;
    private Instant createAt;

    public EmployeeDTO(Integer id, String name, String email, String phone,
                       String role, Boolean status, Instant createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.createAt = createAt;
    }


}
