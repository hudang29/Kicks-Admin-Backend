package com.poly.admin.model;

import com.poly.admin.enums.EmployeeRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Email", nullable = false)
    private String email;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "Phone", nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Nationalized
    @Column(name = "Role", length = 50)
    private EmployeeRoles role;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Address")
    private String address;

    @ColumnDefault("1")
    @Column(name = "Status")
    private Boolean status = true;

    @ColumnDefault("getdate()")
    @Column(name = "Create_at")
    private Instant createAt = Instant.now();

    @Size(max = 100)
    @Nationalized
    @Column(name = "City", length = 100)
    private String city;

    @Size(max = 100)
    @Nationalized
    @Column(name = "District", length = 100)
    private String district;

    @Size(max = 100)
    @Nationalized
    @Column(name = "Ward", length = 100)
    private String ward;

    public Employee(Integer id, String name, String email, String phone,
                    EmployeeRoles role, String address, String city, String district, String ward) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public Employee(String name, String email, String phone,
                    EmployeeRoles role, String address, String city, String district, String ward) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public Employee(String name, String email, String phone,
                    String address, String city, String district, String ward) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.district = district;
        this.ward = ward;
    }

    public Employee(Integer id, Boolean status) {
        this.id = id;
        this.status = status;
    }

    public Employee(String email) {
        this.email = email;
    }
}