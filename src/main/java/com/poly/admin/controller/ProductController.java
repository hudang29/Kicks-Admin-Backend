package com.poly.admin.controller;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.dto.UpdateProductDetail;
import com.poly.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/list-product")
    public List<ProductDTO> showProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/api/list-product/{id}")
    public Optional<ProductDTO> getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/api/product-update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.addOrUpdateProduct(productDTO);
            System.out.println("Cập nhật sản phẩm thành công!");
            return ResponseEntity.ok("Cập nhật sản phẩm thành công!"); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi cập nhật sản phẩm: " + e.getMessage()); // Trả về thông báo lỗi chi tiết
        }
    }


    /*------ Product detail -------*/

    @GetMapping("/api/list-product-detail/{id}")
    public List<ProductDetailDTO> showProductDetailByProductID(@PathVariable("id") Integer id) {
        return productService.getDetailByProductId(id);
    }

    @GetMapping("/api/product-detail/{id}")
    public Optional<ProductDetailDTO> showDetail(@PathVariable("id") Integer id) {
        return productService.getDetailById(id);
    }

    @PutMapping("/api/product-detail-update")
    public ResponseEntity<?> updateDetail(@RequestBody ProductDetailDTO productDetailDTO) {
        try {
            productService.addOrUpdateProductDetail(productDetailDTO);
            System.out.println("Cập nhật chi tiết sản phẩm thành công!");
            return ResponseEntity.ok("Cập nhật chi tiết sản phẩm thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật chi tiết sản phẩm: " + e.getMessage());
        }
    }

    @PostMapping("/api/product-detail-create")
    public ResponseEntity<?> createDetail(@RequestBody UpdateProductDetail updateData) {
        try {
            productService.UpdateProductDetail(updateData);
            return ResponseEntity.ok("thêm chi tiết sản phẩm thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi thêm chi tiết sản phẩm: " + e.getMessage());
        }
    }

}
