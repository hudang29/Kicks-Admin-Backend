package com.poly.admin.controller;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.model.Product;
import com.poly.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/staff/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/page-product")
    public ResponseEntity<Page<ProductDTO>> showPageProducts(@RequestParam(defaultValue = "0") int page) {
        Page<ProductDTO> products = productService.getAllProducts(page);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no products found
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/page-product/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "newest") String sortBy
    ) {
        Page<ProductDTO> products = productService.searchProducts(name, brand, minPrice, maxPrice, page);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/list-product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Integer id) {
        Optional<ProductDTO> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Trả về 404 nếu không tìm thấy
    }


    @PutMapping("/product-update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.UpdateProduct(productDTO);
            ProductDTO newResponse = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getShoesCategory().getId(),
                    product.getGenderCategory().getId(),
                    product.getSupplier().getId(),
                    product.getBrand(),
                    product.getPrice(),
                    product.getDescription()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating product: " + e.getMessage());
        }
    }

    @PostMapping("/product-create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product newProduct = productService.addProduct(productDTO);
            ProductDTO newResponse = new ProductDTO(
                    newProduct.getId(),
                    newProduct.getName(),
                    newProduct.getShoesCategory().getId(),
                    newProduct.getGenderCategory().getId(),
                    newProduct.getSupplier().getId(),
                    newProduct.getBrand(),
                    newProduct.getPrice(),
                    newProduct.getDescription()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product: " + e.getMessage());
        }
    }
}
