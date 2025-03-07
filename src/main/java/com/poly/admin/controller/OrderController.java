package com.poly.admin.controller;

import com.poly.admin.dto.OrderDTO;
import com.poly.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/show-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders-by-status")
    public List<OrderDTO> getOrdersByStatus(@RequestParam(defaultValue = "Pending") String status) {

        return orderService.getOrdersByStatus(status);
    }
}
