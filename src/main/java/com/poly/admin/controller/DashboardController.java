package com.poly.admin.controller;

import com.poly.admin.dto.*;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    LocalDate currentDate = LocalDate.now();
    Integer year = currentDate.getYear();
    Integer month = currentDate.getMonthValue();

    @GetMapping("/sales-graph")
    public ResponseEntity<List<SaleGraphData>> getSalesGraphByMonth() {
        List<SaleGraphData> data = dashboardService.getSalesDataByMonth();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/sales-graph/{year}")
    public ResponseEntity<List<SaleGraphData>> getSalesGraphEachYear(@PathVariable Integer year) {
        List<SaleGraphData> data = dashboardService.getSalesDataEachYear(year);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/sales-graph-year")
    public ResponseEntity<List<SaleGraphData>> getSalesGraphByYear() {
        List<SaleGraphData> data = dashboardService.getSalesDataByYear();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/total-amount")
    public BigDecimal getTotalAmountByStatus(@RequestParam OrderStatus status,
                                             @RequestParam(required = false) Integer year,
                                             @RequestParam(required = false) Integer month) {
        return dashboardService.getRevenueMonthlyByStatus(status, year, month);
    }

    // API lấy tổng doanh thu trong tháng hiện tại
    @GetMapping("/total-revenue")
    public ResponseEntity<BigDecimal> getTotalRevenueThisMonth() {

        BigDecimal revenue = dashboardService.getRevenueMonthlyByStatus(OrderStatus.COMPLETED, year, month);
        return revenue != null ? ResponseEntity.ok(revenue) : ResponseEntity.noContent().build();
    }

    @GetMapping("/total-revenue-orders")
    public ResponseEntity<BigDecimal> getTotalRevenueByOrdersThisMonth() {
        BigDecimal revenue = dashboardService.getTotalRevenueByOrdersThisMonth(year, month);
        return revenue != null ? ResponseEntity.ok(revenue) : ResponseEntity.noContent().build();
    }

    // API lấy tổng doanh thu theo trạng thái trong tháng hiện tại
    @GetMapping("/total-revenue-by-status")
    public ResponseEntity<List<SalesSummaryDTO>> getTotalRevenueByStatus() {
        List<SalesSummaryDTO> data = dashboardService.getTotalRevenueByStatus();
        return data.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(data);
    }

    // API lấy tổng doanh thu theo trạng thái Canceled trong tháng hiện tại
//    @GetMapping("/total-revenue-by-status-canceled")
//    public ResponseEntity<Double> getTotalRevenueByStatusCanceled() {
//        Double data = dashboardService.getTotalRevenueByStatusCanceled();
//        return data != null ? ResponseEntity.ok(data) : ResponseEntity.noContent().build();
//    }
//
//    // API lấy tổng số lượng giày đã bán trong tháng
//    @GetMapping("/total-shoes-sale")
//    public ResponseEntity<Integer> getTotalShoesSaleThisMonth() {
//        Integer data = dashboardService.getTotalShoesSale();
//        return data != null ? ResponseEntity.ok(data) : ResponseEntity.noContent().build();
//    }

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
