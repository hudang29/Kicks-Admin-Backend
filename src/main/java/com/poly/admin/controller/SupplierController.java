package com.poly.admin.controller;

import com.poly.admin.model.Supplier;
import com.poly.admin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/api/show-supplier")
    public List<Supplier> showSupplier() {
        return supplierService.getAllSuppliers();
    }
}
