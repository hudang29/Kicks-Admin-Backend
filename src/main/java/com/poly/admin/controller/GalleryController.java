package com.poly.admin.controller;

import com.poly.admin.dto.GalleryDTO;
import com.poly.admin.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
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

    @PostMapping("/api/add-gallery")
    public ResponseEntity<?> addGallery(@RequestBody GalleryDTO galleryDTO) {
        try {
            galleryService.addGallery(galleryDTO);
            return ResponseEntity.ok("Thêm ảnh thành công!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
