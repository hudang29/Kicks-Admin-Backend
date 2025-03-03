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
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/api/size-sample")
    public List<SizeSample> sizeSamples() {
        return sizeService.getAll();
    }

    @PostMapping("/api/size-sample-create")
    public SizeSample sizeSampleCreate(@RequestBody SizeSample sizeSample) {
        return sizeService.create(sizeSample);
    }

    @DeleteMapping("/api/size-sample-delete/{id}")
    public void sizeSampleDelete(@PathVariable Integer id) {
        sizeService.deleteSizeSample(id);
    }

    /*------ Size -------*/

    @GetMapping("/api/sizes/{productDetailId}")
    public List<SizeDTO> getProductSizes(@PathVariable("productDetailId") Integer id) {
        return sizeService.getAllSizeByProductDetailId(id);
    }

    @PutMapping("/api/sizes/update/{productDetailId}")
    public ResponseEntity<?> updateSizes(@PathVariable Integer productDetailId,
                                         @RequestBody List<SizeDTO> sizeDTOList) {
        try {
            sizeService.addOrUpdateSizeList(sizeDTOList, productDetailId);
            System.out.println("Cập nhật size phẩm thành công!");
            return ResponseEntity.ok("Cập nhật size thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật size: " + e.getMessage());
        }
    }

    @PostMapping("/api/sizes/create/{productDetailId}")
    public ResponseEntity<?> createSizes(@PathVariable Integer productDetailId,
                                         @RequestBody List<SizeDTO> sizeDTOList) {
        try {
            sizeService.addOrUpdateSizeList(sizeDTOList, productDetailId);
            System.out.println("Tạo size phẩm thành công!");
            return ResponseEntity.ok("Create size thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Create size (backend): " + e.getMessage());
        }
    }


}
