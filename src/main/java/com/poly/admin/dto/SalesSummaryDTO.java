package com.poly.admin.dto;

import com.poly.admin.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SalesSummaryDTO {
    private String status;
    private BigDecimal totalAmount;

    public SalesSummaryDTO(OrderStatus status , BigDecimal totalAmount) {
        this.status = (status != null) ? status.name() : null;;
        this.totalAmount = totalAmount;
    }
}
