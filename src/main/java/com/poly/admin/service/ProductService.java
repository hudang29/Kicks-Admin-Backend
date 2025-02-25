package com.poly.admin.service;

import com.poly.admin.dto.*;
import com.poly.admin.dto.UpdateProductDetail;
import com.poly.admin.model.*;
import com.poly.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    @Autowired
    private ShoesCategoryRepo shoesCategoryRepo;
    @Autowired
    private GenderCategoryRepo genderCategoryRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private DiscountRepo discountRepo;

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(product -> new ProductDTO(product.getId(),
                        product.getName(),
                        product.getShoesCategory().getId(),
                        product.getGenderCategory().getId(),
                        product.getSupplier().getId(),
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
                product.getSupplier().getId(),
                product.getBrand(),
                product.getPrice(),
                product.getDescription()
        ));
    }

    public String getNameById(int id) {

        return productRepo.findNameById(id);
    }

    public void addOrUpdateProduct(ProductDTO productDTO) {

        Integer supplierId = productDTO.getSupplierID();
        Integer genderCategoryId = productDTO.getGenderCategoryID();
        Integer shoesCategoryId = productDTO.getShoesCategoryID();
        Integer productId = productDTO.getId();
        Product product;

        if (productId != null) {
            // Nếu ID tồn tại, tìm sản phẩm trong database để cập nhật
            product = productRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại!"));
        } else {
            // Nếu ID không có, tạo mới sản phẩm
            product = new Product();
        }

        // get GenderCategory use orElseThrow
        GenderCategory genderCategory = genderCategoryRepo.findById(genderCategoryId)
                .orElseThrow(() -> new RuntimeException("Danh mục giới tính không tồn tại!"));
        product.setGenderCategory(genderCategory);

        // get ShoesCategory use orElseThrow
        ShoesCategory shoesCategory = shoesCategoryRepo.findById(shoesCategoryId)
                .orElseThrow(() -> new RuntimeException("Danh mục giày không tồn tại!"));
        product.setShoesCategory(shoesCategory);

        // get Supplier use orElseThrow
        Supplier supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Nhà cung cấp không tồn tại!"));
        product.setSupplier(supplier);

        // set up product
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        if (productId == null) {
            product.setCreateAt(Instant.now());
        }

        productRepo.save(product);
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
                        productDetail.getProductDiscount() != null ?
                                productDetail.getProductDiscount().getId() : null
                )).toList();
    }

    public Optional<ProductDetailDTO> getDetailById(int id) {
        return productDetailRepo.findById(id).map(productDetail -> new ProductDetailDTO(
                productDetail.getId(),
                productDetail.getProduct().getId(),
                productDetail.getProduct().getName(),
                productDetail.getColor(),
                productDetail.getProductDiscount() != null ?
                        productDetail.getProductDiscount().getId() : null
        ));
    }

    public void addOrUpdateProductDetail(ProductDetailDTO productDetailDTO) {
        Integer productId = productDetailDTO.getProductId();
        Integer discountId = productDetailDTO.getDiscountId();
        Integer productDetailId = productDetailDTO.getId();

        ProductDetail productDetail;
        if (productDetailId != null) {
            productDetail = productDetailRepo.findById(productDetailId)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại!"));
        } else {
            productDetail = new ProductDetail();
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is null"));
        productDetail.setProduct(product);
        productDetail.setColor(productDetailDTO.getColor());

        Optional<ProductDiscount> optionalDiscount = discountRepo.findById(discountId);
        if (optionalDiscount.isPresent()) {
            productDetail.setProductDiscount(optionalDiscount.get());
        } else {
            productDetail.setProductDiscount(null);
        }
        productDetail.setIsDefault(false);

        productDetailRepo.save(productDetail);
    }
    public void UpdateProductDetail(UpdateProductDetail updateData) {
        Integer productId = updateData.getProductId();

        ProductDetail productDetail = new ProductDetail();

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is null"));
        productDetail.setProduct(product);
        productDetail.setColor(updateData.getColor());
        productDetail.setProductDiscount(null);
        productDetail.setIsDefault(false);

        productDetailRepo.save(productDetail);
    }
}
