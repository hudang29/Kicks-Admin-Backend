package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Coupon_Code", nullable = false, length = 50)
    private String couponCode;

    @Size(max = 500)
    @Nationalized
    @Column(name = "Description", length = 500)
    private String description;

    @NotNull
    @Column(name = "Discount_Rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal discountRate;

    @NotNull
    @Column(name = "Start_Date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "End_Date", nullable = false)
    private LocalDate endDate;

    @ColumnDefault("20")
    @Column(name = "Usage_Limit")
    private Integer usageLimit;

    @ColumnDefault("getdate()")
    @Column(name = "Created_at")
    private Instant createdAt;

}