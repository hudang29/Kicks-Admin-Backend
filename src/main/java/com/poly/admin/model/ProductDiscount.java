package com.poly.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product_discount")
public class ProductDiscount {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Discount_rate", precision = 5, scale = 2)
    private BigDecimal discountRate;

    @NotNull
    @Column(name = "Start_Date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "End_Date", nullable = false)
    private LocalDate endDate;

}