package com.poly.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SalesSummaryDTO {
    private String status;
    private BigDecimal totalAmount;
}
