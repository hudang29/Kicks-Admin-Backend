package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "ShoesCategory_ID")
    private com.poly.admin.model.ShoesCategory shoesCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "GenderCategory_ID")
    private GenderCategory genderCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "SupplierID")
    private com.poly.admin.model.Supplier supplierID;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "DiscountID")
    private Discount discountID;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Size(max = 100)
    @Nationalized
    @Column(name = "Brand", length = 100)
    private String brand;

    @Size(max = 500)
    @Nationalized
    @Column(name = "Description", length = 500)
    private String description;

    @ColumnDefault("getdate()")
    @Column(name = "Create_at")
    private Instant createAt;

}