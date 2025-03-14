package com.poly.admin.controller;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/staff/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> getById(@PathVariable("orderId") int orderId) {
        OrderDTO order = orderService.getById(orderId);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getOrderStatuses() {
        List<String> statuses = Arrays.stream(OrderStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return statuses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(statuses);
    }

    @GetMapping("/show-orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }

    @GetMapping("/orders-by-status")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(
            @RequestParam(defaultValue = "PENDING") OrderStatus status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orders);
    }

}
