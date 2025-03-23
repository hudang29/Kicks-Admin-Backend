package com.poly.admin.service;

import com.poly.admin.dto.*;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.model.Orders;
import com.poly.admin.repository.OrderRepo;
import com.poly.admin.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private OrderRepo orderRepository;


    public List<SaleGraphData> getSalesDataByMonth() {

        return orderRepository.getSalesDataByMonth();
    }

    public List<SaleGraphData> getSalesDataEachYear(Integer year) {
        Specification<Orders> spec = Specification
                .where(OrderSpecification.hasMonthAndYear(year, null))
                .and(OrderSpecification.hasStatus(OrderStatus.COMPLETED));
        return orderRepository.getSalesDataEachYear(spec);
    }


    public List<SaleGraphData> getSalesDataByYear() {
        Specification<Orders> spec = Specification
                .where(OrderSpecification.hasStatus(OrderStatus.COMPLETED));
        return orderRepository.getSalesDataByYear(spec);
    }

    public BigDecimal getTotalRevenueByOrdersThisMonth(Integer year, Integer month) {
        Specification<Orders> spec = Specification
                .where(OrderSpecification.hasMonthAndYear(year, month));
        return orderRepository.getTotalAmountByStatus(spec);
    }

    public List<SalesSummaryDTO> getTotalRevenueByStatus() {
        return orderRepository.getTotalRevenueByStatus();
    }

    public BigDecimal getRevenueMonthlyByStatus(OrderStatus status, Integer year, Integer month) {
        Specification<Orders> spec = Specification
                .where(OrderSpecification.hasMonthAndYear(year, month))
                .and(OrderSpecification.hasStatus(status));
        return orderRepository.getTotalAmountByStatus(spec);
    }


    public List<OrderDTO> getLatestOrders() {

        return orderRepository.findLatestOrders(PageRequest.of(0, 6));
    }

    public List<BestSellerDTO> getTop3BestSellers() {
        Pageable top3 = PageRequest.of(0, 3);
        return orderRepository.findTopBestSellers(top3);
    }

    public List<LowStockProductDTO> getLowStockProducts(int threshold) {
        return orderRepository.findLowStockProducts(threshold);
    }

}
