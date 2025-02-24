package com.poly.admin.controller;

import com.poly.admin.model.SizeSample;
import com.poly.admin.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
