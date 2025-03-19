package com.poly.admin.controller;

import com.poly.admin.dto.*;
import com.poly.admin.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/sales-graph")
    public ResponseEntity<List<SaleGraphData>> getSalesGraphByMonth() {
        List<SaleGraphData> data = dashboardService.getSalesDataByMonth();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/sales-graph/{year}")
    public ResponseEntity<List<SaleGraphData>> getSalesGraphEachYear(@PathVariable int year) {
        List<SaleGraphData> data = dashboardService.getSalesDataEachYear(year);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/sales-graph-year")
    public ResponseEntity<List<SaleGraphData>> getSalesGraphByYear() {
        List<SaleGraphData> data = dashboardService.getSalesDataByYear();
        return ResponseEntity.ok(data);
    }

    // API lấy tổng doanh thu trong tháng hiện tại
    @GetMapping("/total-revenue")
    public ResponseEntity<Double> getTotalRevenueThisMonth() {
        Double revenue = dashboardService.getTotalRevenueThisMonth();
        return revenue != null ? ResponseEntity.ok(revenue) : ResponseEntity.noContent().build();
    }

    @GetMapping("/total-revenue-orders")
    public ResponseEntity<Double> getTotalRevenueByOrdersThisMonth() {
        Double revenue = dashboardService.getTotalRevenueByOrdersThisMonth();
        return revenue != null ? ResponseEntity.ok(revenue) : ResponseEntity.noContent().build();
    }

    // API lấy tổng doanh thu theo trạng thái trong tháng hiện tại
    @GetMapping("/total-revenue-by-status")
    public ResponseEntity<List<SalesSummaryDTO>> getTotalRevenueByStatus() {
        List<SalesSummaryDTO> data = dashboardService.getTotalRevenueByStatus();
        return data.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(data);
    }

    // API lấy top 6 đơn hàng mới nhất
    @GetMapping("/get-latest-orders")
    public ResponseEntity<List<OrderDTO>> getTop6NewOrder() {
        List<OrderDTO> orders = dashboardService.getLatestOrders();
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }

    // API lấy top 3 sản phẩm bán chạy nhất
    @GetMapping("/get-top3-bestseller")
    public ResponseEntity<List<BestSellerDTO>> getTop3BestSellers() {
        List<BestSellerDTO> bestSellers = dashboardService.getTop3BestSellers();
        return bestSellers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bestSellers);
    }

    // API lấy sản phẩm có lượng tồn kho thấp
    @GetMapping("/get-low-stock")
    public ResponseEntity<List<LowStockProductDTO>> getLowStockProducts(
            @RequestParam(defaultValue = "6") int stock) {
        List<LowStockProductDTO> lowStockProducts = dashboardService.getLowStockProducts(stock);
        return lowStockProducts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lowStockProducts);
    }

}
