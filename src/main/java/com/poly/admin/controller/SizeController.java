package com.poly.admin.controller;

import com.poly.admin.dto.SizeDTO;
import com.poly.admin.model.SizeSample;
import com.poly.admin.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/size-sample")
    public ResponseEntity<List<SizeSample>> sizeSamples() {
        List<SizeSample> sizes = sizeService.getAll();
        return sizes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sizes);
    }

    @PostMapping("/size-sample-create")
    public ResponseEntity<SizeSample> sizeSampleCreate(@RequestBody SizeSample sizeSample) {
        SizeSample createdSize = sizeService.create(sizeSample);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSize);
    }

    @DeleteMapping("/size-sample-delete/{id}")
    public ResponseEntity<?> sizeSampleDelete(@PathVariable Integer id) {
        try {
            sizeService.deleteSizeSample(id);
            return ResponseEntity.ok("Deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error deleting size sample: " + e.getMessage());
        }
    }

    /*------ Size -------*/

    @GetMapping("/sizes/{productDetailId}")
    public ResponseEntity<List<SizeDTO>> getProductSizes(@PathVariable("productDetailId") Integer id) {
        List<SizeDTO> sizes = sizeService.getAllSizeByProductDetailId(id);
        return sizes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sizes);
    }

    @PutMapping("/sizes/update/{productDetailId}")
    public ResponseEntity<?> updateSizes(@PathVariable Integer productDetailId,
                                         @RequestBody List<SizeDTO> sizeDTOList) {
        try {
            sizeService.UpdateSizeList(sizeDTOList, productDetailId);
            return ResponseEntity.ok("Updated sizes successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating sizes: " + e.getMessage());
        }
    }

    @PostMapping("/sizes/create/{productDetailId}")
    public ResponseEntity<?> createSizes(@PathVariable Integer productDetailId,
                                         @RequestBody List<SizeDTO> sizeDTOList) {
        try {
            sizeService.addOrUpdateSizeList(sizeDTOList, productDetailId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sizes created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating sizes: " + e.getMessage());
        }
    }
}
