package com.poly.admin.controller;

import com.poly.admin.dto.GalleryDTO;
import com.poly.admin.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    @GetMapping("/api/product-gallery/{productId}")
    public String showProductGallery(@PathVariable Integer productId) {
        return galleryService.getGalleryByProductId(productId);
    }

    @GetMapping("/api/product-detail-gallery/{productDetailId}")
    public String showProductDetailGallery(@PathVariable Integer productDetailId) {
        return galleryService.getGalleryByProductDetailId(productDetailId);
    }

    @GetMapping("/api/product-detail-galleries/{productDetailId}")
    public List<GalleryDTO> showGalleryByProductDetailId(@PathVariable Integer productDetailId) {
        return galleryService.getAllGalleryByProductDetailId(productDetailId);
    }
}
