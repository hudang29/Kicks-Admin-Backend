package com.poly.admin.service;

import com.poly.admin.dto.*;
import com.poly.admin.model.*;
import com.poly.admin.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private ShoesCategoryRepo shoesCategoryRepo;
    @Autowired
    private GenderCategoryRepo genderCategoryRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private DiscountRepo discountRepo;

    public Page<ProductDTO> getAllProducts(int page) {
        int size = 9; // 6 sản phẩm mỗi trang
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAllByOrderByCreateAtDesc(pageable)
                .map(product -> new ProductDTO(product.getId(),
                        product.getName(),
                        product.getShoesCategory().getId(),
                        product.getGenderCategory().getId(),
                        product.getSupplier().getId(),
                        product.getBrand(),
                        product.getPrice(),
                        product.getDescription()));
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

    public Product addProduct(ProductDTO productDTO){
        Integer supplierId = productDTO.getSupplierID();
        Integer genderCategoryId = productDTO.getGenderCategoryID();
        Integer shoesCategoryId = productDTO.getShoesCategoryID();

        GenderCategory genderCategory = genderCategoryRepo.findById(genderCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Gender category does not exist!"));

        ShoesCategory shoesCategory = shoesCategoryRepo.findById(shoesCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Shoes category does not exist!"));

        Supplier supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier does not exist!"));

        Product product = new Product(shoesCategory,
                genderCategory,
                supplier,
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getBrand(),
                productDTO.getDescription(),
                Instant.now());

        return productRepo.save(product);
    }

    public Product UpdateProduct(ProductDTO productDTO) {

        Integer supplierId = productDTO.getSupplierID();
        Integer genderCategoryId = productDTO.getGenderCategoryID();
        Integer shoesCategoryId = productDTO.getShoesCategoryID();
        Integer productId = productDTO.getId();
        Product product = new Product();

        if (productId != null) {
            product = productRepo.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product does not exist!"));
        }
        // get GenderCategory use orElseThrow
        GenderCategory genderCategory = genderCategoryRepo.findById(genderCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Gender category does not exist!"));
        product.setGenderCategory(genderCategory);

        // get ShoesCategory use orElseThrow
        ShoesCategory shoesCategory = shoesCategoryRepo.findById(shoesCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Shoes category does not exist!"));
        product.setShoesCategory(shoesCategory);

        // get Supplier use orElseThrow
        Supplier supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier does not exist!"));
        product.setSupplier(supplier);

        // set up product
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setBrand(productDTO.getBrand());
        product.setDescription(productDTO.getDescription());
        return productRepo.save(product);
    }


    /*------ Product detail -------*/

    public List<String> getColorByProductId(int id) {
        return productDetailRepo.findColorByProductId(id);
    }


    public List<ProductDetailDTO> getDetailByProductId(int id) {
        return productDetailRepo.findByProductId(id)
                .stream()
                .map(productDetail -> new ProductDetailDTO(
                        productDetail.getId(),
                        productDetail.getProduct().getId(),
                        productDetail.getColor(),
                        productDetail.getProductDiscount() != null ?
                                productDetail.getProductDiscount().getId() : null,
                        productDetail.getIsDefault()
                )).toList();
    }

    public Optional<ProductDetailDTO> getDetailById(int id) {
        return productDetailRepo.findById(id).map(productDetail -> new ProductDetailDTO(
                productDetail.getId(),
                productDetail.getProduct().getId(),
                productDetail.getColor(),
                productDetail.getProductDiscount() != null ?
                        productDetail.getProductDiscount().getId() : null,
                productDetail.getIsDefault()
        ));
    }

    public ProductDetail updateProductDetail(ProductDetailDTO productDetailDTO) {
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
        //productDetail.setIsDefault(false);

        return productDetailRepo.save(productDetail);
    }

    public ProductDetail addProductDetail(ProductDetailDTO updateData) {
        Integer productId = updateData.getProductId();

        ProductDetail productDetail = new ProductDetail();

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is null"));
        productDetail.setProduct(product);
        productDetail.setColor(updateData.getColor());
        productDetail.setProductDiscount(discountRepo.findById(1).orElse(null));
        productDetailRepo.save(productDetail);
        return productDetailRepo.save(productDetail);
    }
}
