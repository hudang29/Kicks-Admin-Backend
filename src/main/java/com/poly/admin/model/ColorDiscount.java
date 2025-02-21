package com.poly.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Color_discount")
public class ColorDiscount {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Discount_rate", precision = 5, scale = 2)
    private BigDecimal discountRate;

    @NotNull
    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;

}