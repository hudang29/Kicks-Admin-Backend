package com.poly.admin.service;

import com.poly.admin.dto.SizeDTO;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.model.ProductSize;
import com.poly.admin.model.SizeSample;
import com.poly.admin.repository.ProductDetailRepo;
import com.poly.admin.repository.ProductSizeRepo;
import com.poly.admin.repository.SizeRepo;
import com.poly.admin.utils.ValidationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SizeService {
    @Autowired
    private SizeRepo sizeRepo;
    @Autowired
    private ProductSizeRepo productSizeRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ValidationForm validationForm;

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
                        size.getProductDetail().getId(),
                        size.getSize(),
                        size.getStock()
                )).toList();
    }

    public void addOrUpdateSizeList(List<SizeDTO> sizeDTOList, Integer productDetailId) {
        ProductDetail productDetail = productDetailRepo.findById(productDetailId)
                .orElseThrow(() -> new RuntimeException("Product detail not found"));

        // Lấy danh sách size hiện có trong DB
        List<ProductSize> existingSizes = productSizeRepo.findByProductDetail_Id(productDetailId);

        // Chuyển danh sách hiện có thành Map để tìm nhanh hơn
        Map<String, ProductSize> sizeMap = existingSizes.stream()
                .collect(Collectors.toMap(ProductSize::getSize, size -> size));

        List<ProductSize> updatedProductSizes = new ArrayList<>();

        for (SizeDTO sizeDTO : sizeDTOList) {
            if (sizeMap.containsKey(sizeDTO.getSize())) {
                // Nếu kích thước đã tồn tại => cập nhật stock
                ProductSize existingSize = sizeMap.get(sizeDTO.getSize());
                if (!validationForm.isValidStock(sizeDTO.getStock())) {
                    throw new IllegalArgumentException("Invalid input");
                }
                existingSize.setStock(sizeDTO.getStock());
                updatedProductSizes.add(existingSize);
            } else {
                // Nếu kích thước chưa tồn tại => tạo mới
                ProductSize newSize = new ProductSize();
                newSize.setSize(sizeDTO.getSize());
                if (!validationForm.isValidStock(sizeDTO.getStock())) {
                    throw new IllegalArgumentException("Invalid input");
                }
                newSize.setStock(sizeDTO.getStock());
                newSize.setProductDetail(productDetail);
                updatedProductSizes.add(newSize);
            }
        }

        // Lưu danh sách đã cập nhật hoặc thêm mới vào DB
        productSizeRepo.saveAll(updatedProductSizes);
    }

}
