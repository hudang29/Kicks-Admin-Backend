package com.poly.admin.service;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.model.Employee;
import com.poly.admin.model.Orders;
import com.poly.admin.repository.EmployeeRepo;
import com.poly.admin.repository.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

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

    public OrderDTO changeOrderStatus(OrderDTO orderDTO) {
        Integer orderId = orderDTO.getId();
        Integer employeeId = orderDTO.getEmployeeId();

        Orders order = orderRepo.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("Not found order")
        );

        Employee employee = employeeRepo.findById(employeeId).orElseThrow(
                () -> new IllegalArgumentException("Not found employee")
        );

        if (order.getOrderStatus().name().equals(OrderStatus.CANCELLED.name()) ||
                order.getOrderStatus().name().equals(OrderStatus.COMPLETED.name())) {
            throw new IllegalArgumentException("Order is already completed or cancelled");
        }

        try {
            order.setOrderStatus(OrderStatus.valueOf(orderDTO.getOrderStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order status: " + orderDTO.getOrderStatus());
        }

        order.setEmployee(employee);

        Orders savedOrder = orderRepo.save(order);

        return new OrderDTO(
                savedOrder.getId(),
                savedOrder.getCoupon() != null ? savedOrder.getCoupon().getId() : null,
                savedOrder.getEmployee() != null ? savedOrder.getEmployee().getId() : null,
                savedOrder.getOrderDate(),
                savedOrder.getPayment().getPaymentMethod(),
                savedOrder.getCustomer().getName(),
                savedOrder.getCustomer().getPhone(),
                savedOrder.getOrderStatus(),
                savedOrder.getTotalAmount(),
                savedOrder.getShippingAddress() != null ? order.getShippingAddress() : null
        );
    }
}
