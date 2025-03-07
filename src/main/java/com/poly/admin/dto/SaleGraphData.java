package com.poly.admin.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class SaleGraphData {
    private Integer  period;
    private BigDecimal totalRevenue;

    public SaleGraphData(Integer  period, BigDecimal totalRevenue) {
        this.period = period;
        this.totalRevenue = totalRevenue;
    }

    public Integer  getPeriod() {
        return period;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
