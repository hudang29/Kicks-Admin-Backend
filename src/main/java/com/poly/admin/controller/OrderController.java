package com.poly.admin.controller;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/api/show-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
}
