package com.poly.admin.service;

import com.poly.admin.dto.*;
import com.poly.admin.repository.OrderDetailRepo;
import com.poly.admin.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private OrderDetailRepo orderDetailRepository;

    public List<SaleGraphData> getSalesDataByMonth() {
        return orderRepository.getSalesDataByMonth();
    }

    public List<SaleGraphData> getSalesDataEachYear(int year) {
        return orderRepository.getSalesDataEachYear(year);
    }


    public List<SaleGraphData> getSalesDataByYear() {
        return orderRepository.getSalesDataByYear();
    }

    public Double getTotalRevenueThisMonth() {
        return orderRepository.getTotalRevenueThisMonth();
    }

    public Double getTotalRevenueByOrdersThisMonth() {
        return orderRepository.getTotalRevenueByOrdersThisMonth();
    }

    public List<SalesSummaryDTO> getTotalRevenueByStatus() {
        return orderRepository.getTotalRevenueByStatus();
    }

    public Double getTotalRevenueByStatusCanceled() {
        return orderRepository.getTotalRevenueByStatusCanceled();
    }

    public Integer getTotalShoesSale(){
        return orderDetailRepository.getTotalShoesSale();
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
