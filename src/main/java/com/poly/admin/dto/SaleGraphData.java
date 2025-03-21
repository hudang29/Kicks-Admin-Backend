package com.poly.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class SaleGraphData {
    private Integer  period;
    private BigDecimal totalRevenue;

    public SaleGraphData(Integer  period, BigDecimal totalRevenue) {
        this.period = period;
        this.totalRevenue = totalRevenue;
    }

    public SaleGraphData() {

    }

}
