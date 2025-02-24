package com.poly.admin.service;

import com.poly.admin.model.SizeSample;
import com.poly.admin.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepo sizeRepo;

    public List<SizeSample> getAll() {
        return sizeRepo.findAll();
    }

    public SizeSample create(SizeSample sizeSample) {
        return sizeRepo.save(sizeSample);
    }

    public void deleteSizeSample(int id) {
        sizeRepo.deleteById(id);
    }
}
