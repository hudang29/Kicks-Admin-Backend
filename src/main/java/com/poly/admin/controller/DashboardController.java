package com.poly.admin.controller;

import com.poly.admin.dto.*;
import com.poly.admin.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/sales-graph")
    public List<SaleGraphData> getSalesGraphByMonth() {
        return dashboardService.getSalesDataByMonth();
    }

    @GetMapping("/sales-graph-year")
    public List<SaleGraphData> getSalesGraphByYear() {
        return dashboardService.getSalesDataByYear();
    }

    // API lấy tổng doanh thu trong tháng hiện tại
    @GetMapping("/total-revenue")
    public Double getTotalRevenueThisMonth() {
        return dashboardService.getTotalRevenueThisMonth();
    }

    @GetMapping("/total-revenue-orders")
    public Double getTotalRevenueByOrdersThisMonth() {
        return dashboardService.getTotalRevenueByOrdersThisMonth();
    }

    // API lấy tổng doanh thu theo trạng thái trong tháng hiện tại
    @GetMapping("/total-revenue-by-status")
    public List<SalesSummaryDTO> getTotalRevenueByStatus() {

        return dashboardService.getTotalRevenueByStatus();
    }

    // API lấy top 6 đơn hàng mới nhất
    @GetMapping("/get-latest-orders")
    public List<OrderDTO> getTop6NewOrder() {
        return dashboardService.getLatestOrders();
    }

    // API lấy top 3 sản phẩm bán chạy nhất
    @GetMapping("/get-top3-bestseller")
    public ResponseEntity<?> getTop3BestSellers() {
        List<BestSellerDTO> bestSellers = dashboardService.getTop3BestSellers();
        return ResponseEntity.ok(bestSellers);
    }
    // API lấy top 3 sản phẩm bán chạy nhất
    // Ex:http://localhost:8080/api/dashboard/get-low-stock?threshold=5
    @GetMapping("/get-low-stock")
    public ResponseEntity<List<LowStockProductDTO>> getLowStockProducts(
            @RequestParam(defaultValue = "10") int threshold) {
        List<LowStockProductDTO> lowStockProducts = dashboardService.getLowStockProducts(threshold);
        return ResponseEntity.ok(lowStockProducts);
    }
}
