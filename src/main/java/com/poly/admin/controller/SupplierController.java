package com.poly.admin.controller;

import com.poly.admin.model.Supplier;
import com.poly.admin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/api/show-supplier/{id}")
    public Supplier showSupplier(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }
}
