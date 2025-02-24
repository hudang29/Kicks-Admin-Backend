package com.poly.admin.service;

import com.poly.admin.dto.SizeDTO;
import com.poly.admin.model.ProductSize;
import com.poly.admin.model.SizeSample;
import com.poly.admin.repository.ProductSizeRepo;
import com.poly.admin.repository.SizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ProductSizeRepo productSizeRepo;

    public List<SizeSample> getAll() {
        return sizeRepo.findAll();
    }

    public SizeSample create(SizeSample sizeSample) {
        return sizeRepo.save(sizeSample);
    }

    public void deleteSizeSample(int id) {
        sizeRepo.deleteById(id);
    }

    public List<SizeDTO> getAllSizeByProductDetailId(int id) {
        System.out.println(id);
        return productSizeRepo.findByProductDetail_Id(id)
                .stream()
                .map(size -> new SizeDTO(
                        size.getId(),
                        size.getSize(),
                        size.getStock()
                )).toList();
    }

    public void addSizeList(List<ProductSize> productSizeList) {
        productSizeRepo.saveAll(productSizeList);
    }
}
