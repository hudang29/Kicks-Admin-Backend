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
    public ResponseEntity<String> showProductGallery(@PathVariable Integer productId) {
        String gallery = galleryService.getGalleryByProductId(productId);
        return (gallery != null && !gallery.isEmpty()) ?
                ResponseEntity.ok(gallery) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/product-detail-gallery/{productDetailId}")
    public ResponseEntity<String> showProductDetailGallery(@PathVariable Integer productDetailId) {
        String gallery = galleryService.getGalleryByProductDetailId(productDetailId);
        return (gallery != null && !gallery.isEmpty()) ?
                ResponseEntity.ok(gallery) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/product-detail-galleries/{productDetailId}")
    public ResponseEntity<List<GalleryDTO>> showGalleryByProductDetailId(@PathVariable Integer productDetailId) {
        List<GalleryDTO> galleries = galleryService.getAllGalleryByProductDetailId(productDetailId);
        return galleries.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(galleries);
    }

    @PostMapping("/api/add-gallery")
    public ResponseEntity<String> addGallery(@RequestBody GalleryDTO galleryDTO) {
        try {
            galleryService.addGallery(galleryDTO);
            return ResponseEntity.ok("Gallery added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

}
