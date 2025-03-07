package com.poly.admin.service;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.model.Orders;
import com.poly.admin.repository.OrderDetailRepo;
import com.poly.admin.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        return orders.stream().map(order -> new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getPayment().getPaymentMethod(),
                order.getCustomer().getName(),
                order.getOrderStatus(),
                order.getTotalAmount()
        )).toList();
    }

    public List<OrderDTO> getOrdersByStatus(String status) {
        return orderRepo.findAllByOrderStatusEqualsIgnoreCaseOrderByOrderDateAsc(status)
                .stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getOrderDate(),
                        order.getPayment().getPaymentMethod(),
                        order.getCustomer().getName(),
                        order.getOrderStatus(),
                        order.getTotalAmount()
                )).toList();
    }
}
