package com.poly.admin.service;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.dto.SizeDTO;
import com.poly.admin.model.Product;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.repository.ProductDetailRepo;
import com.poly.admin.repository.ProductRepo;
import com.poly.admin.repository.ProductSizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ProductSizeRepo productSizeRepo;

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(product -> new ProductDTO(product.getId(),
                        product.getName(),
                        product.getShoesCategory().getId(),
                        product.getGenderCategory().getId(),
                        product.getBrand(),
                        product.getPrice(),
                        product.getDescription())).toList();
    }

    public Optional<ProductDTO> getProductById(int id) {
        return productRepo.findById(id).map(product -> new ProductDTO(
                product.getId(),
                product.getName(),
                product.getShoesCategory().getId(),
                product.getGenderCategory().getId(),
                product.getBrand(),
                product.getPrice(),
                product.getDescription()
        ));
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
                        productDetail.getProduct().getId(),
                        productDetail.getProduct().getName(),
                        productDetail.getColor(),
                        productDetail.getColorDiscount() != null ?
                                productDetail.getColorDiscount().getId() : null
                )).toList();
    }

    public Optional<ProductDetailDTO> getDetailById(int id) {
        return productDetailRepo.findById(id).map(productDetail -> new ProductDetailDTO(
                productDetail.getId(),
                productDetail.getProduct().getId(),
                productDetail.getProduct().getName(),
                productDetail.getColor(),
                productDetail.getColorDiscount() != null ?
                        productDetail.getColorDiscount().getId() : null
        ));
    }

    /*------ Product size -------*/

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
}
