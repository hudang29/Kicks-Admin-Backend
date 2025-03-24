package com.poly.admin.service;

import com.poly.admin.model.Supplier;
import com.poly.admin.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    public Supplier getSupplierById(int id) {
        return supplierRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Supplier not found")
        );
    }

    public Supplier addSupplier(Supplier supplier) {
        if (supplier.getName().isEmpty() ||
                supplier.getAddress().isEmpty() || supplier.getContactInfo().isEmpty()) {
            throw new IllegalArgumentException("Supplier data is empty");
        }
        return supplierRepo.save(supplier);
    }

    public Supplier updateSupplier(Supplier supplier) {
        if (!supplierRepo.existsById(supplier.getId())) {
            throw new IllegalArgumentException("Supplier not found");
        }
        if (supplier.getName().isEmpty() ||
                supplier.getAddress().isEmpty() || supplier.getContactInfo().isEmpty()) {
            throw new IllegalArgumentException("Supplier data is empty");
        }
        return supplierRepo.save(supplier);
    }
}
