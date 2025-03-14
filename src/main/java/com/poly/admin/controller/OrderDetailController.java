package com.poly.admin.controller;

import com.poly.admin.dto.OrderDetailDTO;
import com.poly.admin.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/order-details/{id}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetails(@PathVariable int id) {
        List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(id);
        return orderDetails.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(orderDetails);
    }

}
