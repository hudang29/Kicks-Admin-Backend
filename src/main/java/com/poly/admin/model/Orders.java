package com.poly.admin.model;

import com.poly.admin.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"Orders\"")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "Customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "Employee")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "Payment")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "Coupon")
    private Coupon coupon;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Shipping_address")
    private String shippingAddress;

    @NotNull
    @Column(name = "Total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ColumnDefault("getdate()")
    @Column(name = "Order_date")
    private Instant orderDate;

    @Enumerated(EnumType.STRING)
    @Nationalized
    @ColumnDefault("'PENDING'")
    @Column(name = "Order_status", length = 50)
    private OrderStatus orderStatus;

    public String getOrderStatusString() {
        return this.orderStatus.name();
    }

}