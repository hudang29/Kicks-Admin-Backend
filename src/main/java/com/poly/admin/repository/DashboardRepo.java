package com.poly.admin.repository;

import com.poly.admin.dto.SaleGraphData;
import com.poly.admin.model.Orders;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public interface DashboardRepo {
    BigDecimal getTotalAmountByStatus(Specification<Orders> spec);

    List<SaleGraphData> getSalesDataEachYear(Specification<Orders> spec);

    List<SaleGraphData> getSalesDataByYear(Specification<Orders> spec);
}
