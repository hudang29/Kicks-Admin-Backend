package com.poly.admin.controller;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.model.Product;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/staff")
public class ProductController {
    @Autowired
    private ProductService productService;

    //    @GetMapping("/api/list-product")
//    public List<ProductDTO> showProducts() {
//        return productService.getAllProducts();
//    }
    @GetMapping("/api/page-product")
    public ResponseEntity<Page<ProductDTO>> showPageProducts(@RequestParam(defaultValue = "0") int page) {
        Page<ProductDTO> products = productService.getAllProducts(page);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no products found
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/api/list-product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Integer id) {
        Optional<ProductDTO> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Trả về 404 nếu không tìm thấy
    }


    @PutMapping("/api/product-update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.addOrUpdateProduct(productDTO);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating product: " + e.getMessage());
        }
    }

    @PostMapping("/api/product-create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product newProduct = productService.addOrUpdateProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct); // Use 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product: " + e.getMessage());
        }
    }

    /*------ Product detail -------*/

    @GetMapping("/api/list-color/{productId}")
    public ResponseEntity<List<String>> showColorByProductID(@PathVariable("productId") Integer id){
        List<String> colors = productService.getColorByProductId(id);
        if (colors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colors);
    }

    @GetMapping("/api/list-product-detail/{productId}")
    public ResponseEntity<List<ProductDetailDTO>> showProductDetailByProductID(@PathVariable("productId") Integer id) {
        List<ProductDetailDTO> details = productService.getDetailByProductId(id);
        if (details.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no details found
        }
        return ResponseEntity.ok(details);
    }

    @GetMapping("/api/product-detail/{id}")
    public ResponseEntity<ProductDetailDTO> showDetail(@PathVariable("id") Integer id) {
        Optional<ProductDetailDTO> detail = productService.getDetailById(id);
        return detail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
    }


    @PutMapping("/api/product-detail-update")
    public ResponseEntity<?> updateDetail(@RequestBody ProductDetailDTO productDetailDTO) {
        try {
            ProductDetail productDetail = productService.updateProductDetail(productDetailDTO);
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

    @PostMapping("/api/product-detail-create")
    public ResponseEntity<?> createDetail(@RequestBody ProductDetailDTO newData) {
        try {
            ProductDetail newDetail =  productService.addProductDetail(newData);
            return ResponseEntity.ok(newDetail.getColor());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product detail: " + e.getMessage());
        }
    }

}
