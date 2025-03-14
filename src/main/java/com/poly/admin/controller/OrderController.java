package com.poly.admin.controller;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.enums.OrderStatus;
import com.poly.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrderDTO getById(@PathVariable("orderId") int orderId) {
        return orderService.getById(orderId);
    }

    @GetMapping("/statuses")
    public List<String> getOrderStatuses() {
        return Arrays.stream(OrderStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/show-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders-by-status")
    public List<OrderDTO> getOrdersByStatus(@RequestParam(defaultValue = "PENDING") OrderStatus status) {

        return orderService.getOrdersByStatus(status);
    }
}
