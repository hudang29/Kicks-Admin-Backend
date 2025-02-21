package com.poly.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class Supplier {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Address")
    private String address;

    @Size(max = 100)
    @Nationalized
    @Column(name = "Contact_info", length = 100)
    private String contactInfo;

}