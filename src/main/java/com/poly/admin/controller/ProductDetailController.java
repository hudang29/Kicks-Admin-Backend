package com.poly.admin.controller;

import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff/api")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/list-color/{productId}")
    public ResponseEntity<List<String>> showColorByProductID(@PathVariable("productId") Integer id){
        List<String> colors = productDetailService.getColorByProductId(id);
        if (colors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colors);
    }

    @GetMapping("/list-product-detail/{productId}")
    public ResponseEntity<List<ProductDetailDTO>> showProductDetailByProductID(@PathVariable("productId") Integer id) {
        List<ProductDetailDTO> details = productDetailService.getDetailByProductId(id);
        if (details.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no details found
        }
        return ResponseEntity.ok(details);
    }

    @GetMapping("/product-detail/{id}")
    public ResponseEntity<ProductDetailDTO> showDetail(@PathVariable("id") Integer id) {
        Optional<ProductDetailDTO> detail = productDetailService.getDetailById(id);
        return detail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
    }


    @PutMapping("/product-detail-update")
    public ResponseEntity<?> updateDetail(@RequestBody ProductDetailDTO productDetailDTO) {
        try {
            ProductDetail productDetail = productDetailService.updateProductDetail(productDetailDTO);
            ProductDetailDTO detailDTO = new ProductDetailDTO(
                    productDetail.getId(),
                    productDetail.getProduct().getId(),
                    productDetail.getColor(),
                    productDetail.getProductDiscount().getId(),
                    productDetail.getIsDefault()
            );
            return ResponseEntity.ok(detailDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating product detail: " + e.getMessage());
        }
    }

    @PostMapping("/product-detail-create")
    public ResponseEntity<?> createDetail(@RequestBody ProductDetailDTO newData) {
        try {
            ProductDetail newDetail =  productDetailService.addProductDetail(newData);
            ProductDetailDTO newResponse = new ProductDetailDTO(
                    newDetail.getId(),
                    newDetail.getProduct().getId(),
                    newDetail.getColor(),
                    newDetail.getProductDiscount().getId(),
                    newDetail.getIsDefault()
            );
            return ResponseEntity.ok(newResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product detail: " + e.getMessage());
        }
    }
}
