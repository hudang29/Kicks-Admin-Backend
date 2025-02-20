package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer_address")
public class CustomerAddress {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Customer_ID")
    private Customer customer;

    @Size(max = 255)
    @Nationalized
    @Column(name = "City")
    private String city;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Ward")
    private String ward;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Street")
    private String street;

    @ColumnDefault("0")
    @Column(name = "Is_default")
    private Boolean isDefault;

}