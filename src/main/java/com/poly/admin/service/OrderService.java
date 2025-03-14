package com.poly.admin.service;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.model.Orders;
import com.poly.admin.repository.OrderDetailRepo;
import com.poly.admin.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public OrderDTO getById(Integer id) {
        Optional<Orders> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isEmpty()) {
            return null;
        }

        Orders order = optionalOrder.get();
        return new OrderDTO(
                order.getId(),
                order.getCoupon() != null ? order.getCoupon().getId() : null,
                order.getOrderDate(),
                order.getPayment().getPaymentMethod(),
                order.getCustomer().getName(),
                order.getCustomer().getPhone(),
                order.getOrderStatus(),
                order.getTotalAmount(),
                order.getShippingAddress() != null ? order.getShippingAddress() : null
        );
    }

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

    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        return orderRepo.findAllByOrderStatusOrderByOrderDateAsc(status)
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
