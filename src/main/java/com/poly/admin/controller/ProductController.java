package com.poly.admin.controller;

import com.poly.admin.dto.ProductDTO;
import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.dto.SizeDTO;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.service.CategoryService;
import com.poly.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/show-products")
    public List<ProductDTO> showProducts() {
        return productService.getAllProducts();
    }

    /*------ Product detail -------*/

    @GetMapping("/api/show-product/{id}")
    public List<ProductDetailDTO> showProductByID(@PathVariable("id") Integer id) {
        return productService.getDetailByProductId(id);
    }

    @GetMapping("/api/product-detail/{id}")
    public List<SizeDTO> showDetail(@PathVariable("id") Integer id) {
        return productService.getAllSizes(id);
    }


}
