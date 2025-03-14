package com.poly.admin.service;

import com.poly.admin.dto.OrderDetailDTO;
import com.poly.admin.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepo orderDetailRepository;

    public List<OrderDetailDTO> getOrderDetailsByOrderId(Integer id ) {
        return orderDetailRepository.findOrderDetailByID(id);
    }

}
