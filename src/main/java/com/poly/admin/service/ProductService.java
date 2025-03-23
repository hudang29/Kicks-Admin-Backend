package com.poly.admin.service;

import com.poly.admin.dto.*;
import com.poly.admin.model.*;
import com.poly.admin.repository.*;
import com.poly.admin.specification.ProductSpecification;
import com.poly.admin.utils.ValidationForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ShoesCategoryRepo shoesCategoryRepo;
    @Autowired
    private GenderCategoryRepo genderCategoryRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private ValidationForm validationForm;

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

    public Product addProduct(ProductDTO productDTO) {
        Integer supplierId = productDTO.getSupplierID();
        Integer genderCategoryId = productDTO.getGenderCategoryID();
        Integer shoesCategoryId = productDTO.getShoesCategoryID();
        BigDecimal price = productDTO.getPrice();
        if (!validationForm.isValidPrice(price)){
            throw new IllegalArgumentException("Invalid input");
        }

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

    public Page<ProductDTO> searchProducts(String name, String brand,
                                           Double minPrice, Double maxPrice, int page,
                                           String olderOrNewest) {
        int size = 9;

        Specification<Product> spec = Specification
                .where(ProductSpecification.hasName(name))
                .and(ProductSpecification.hasBrand(brand))
                .and(ProductSpecification.hasPriceBetween(minPrice, maxPrice));

        Sort sort;

        if (olderOrNewest.equalsIgnoreCase("older")) {
            sort = Sort.by("createAt").ascending();
        } else {
            sort = Sort.by("createAt").descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> products = productRepo.findAll(spec, pageable);

        return products.map(product -> new ProductDTO(
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
}
