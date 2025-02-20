package com.poly.admin.service;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.model.Product;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.repository.ProductDetailRepo;
import com.poly.admin.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(product -> new ProductDTO(product.getId(),
                        product.getName(),
                        product.getCategory().getId(),
                        product.getPrice(),
                        product.getDescription())).toList();
    }

    public String getNameById(int id) {
        return productRepo.findNameById(id);
    }


    /*------ Product detail -------*/

    public List<ProductDetailDTO> getDetailByProductId(int id) {
        return productDetailRepo.findByProductId(id)
                .stream()
                .map(productDetail -> new ProductDetailDTO(
                        productDetail.getId(),
                        productRepo.findNameById(id),
                        productDetail.getColor(),
                        productDetail.getColorDiscount() != null ?
                                productDetail.getColorDiscount().getId() : null
                )).toList();
    }
//    public List<ProductDetail> getDetailByProductId(int id) {
//        return productDetailRepo.findByProductId(id);
//    }
}
