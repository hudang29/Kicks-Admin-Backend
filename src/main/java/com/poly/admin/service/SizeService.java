package com.poly.admin.service;

import com.poly.admin.model.Size;
import com.poly.admin.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepo sizeRepo;

    public List<Size> getAll() {
        return sizeRepo.findAll();
    }
}
