package com.poly.admin.controller;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.dto.SizeDTO;
import com.poly.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    /*------ Product detail -------*/

    @GetMapping("/api/list-product-detail/{id}")
    public List<ProductDetailDTO> showProductByID(@PathVariable("id") Integer id) {
        return productService.getDetailByProductId(id);
    }

    @GetMapping("/api/product-detail/{id}")
    public Optional<ProductDetailDTO> showDetail(@PathVariable("id") Integer id) {
        return productService.getDetailById(id);
    }

    /*------ Size -------*/
    @GetMapping("/api/sizes/{id}")
    public List<SizeDTO> getSizes(@PathVariable("id") Integer id) {
        return productService.getAllSizeByProductDetailId(id);
    }

}
