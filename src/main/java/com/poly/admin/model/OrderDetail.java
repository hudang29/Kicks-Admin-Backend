package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_detail")
public class OrderDetail {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Order_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "Product_ID")
    private Product product;

    @Column(name = "Price", precision = 18, scale = 2)
    private BigDecimal price;

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "\"Size\"", nullable = false, length = 10)
    private String size;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Color", nullable = false, length = 50)
    private String color;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

}