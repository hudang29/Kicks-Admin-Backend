package com.poly.admin.dto;

import com.poly.admin.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private Integer couponId;
    private Instant orderDate;
    private String payment;
    private String customer;
    private String phone;
    private String orderStatus;
    private BigDecimal totalAmount;
    private String shippingAddress;

    public OrderDTO(Integer id, Instant orderDate, String payment, String customer, OrderStatus orderStatus, BigDecimal totalAmount) {
        this.id = id;
        this.orderDate = orderDate;
        this.payment = payment;
        this.customer = customer;
        this.orderStatus = (orderStatus != null) ? orderStatus.name() : null;
        this.totalAmount = totalAmount;
    }

    public OrderDTO(Integer id, Instant orderDate, String payment,
                    String customer, String orderStatus, BigDecimal totalAmount) {
        this.id = id;
        this.orderDate = orderDate;
        this.payment = payment;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }

    public OrderDTO(Integer id, String customer, String shippingAddress,
                    Instant orderDate,String orderStatus, BigDecimal totalAmount) {
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }
}
