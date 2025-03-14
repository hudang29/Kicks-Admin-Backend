package com.poly.admin.controller;

import com.poly.admin.model.Supplier;
import com.poly.admin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff/api")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/show-supplier")
    public ResponseEntity<List<Supplier>> showSupplier() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return suppliers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(suppliers);
    }

    @GetMapping("/show-supplier/{id}")
    public ResponseEntity<?> showSupplier(@PathVariable int id) {
        try {
            Supplier supplier = supplierService.getSupplierById(id);
            return ResponseEntity.ok(supplier);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
