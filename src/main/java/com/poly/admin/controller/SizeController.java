package com.poly.admin.controller;

import com.poly.admin.model.Size;
import com.poly.admin.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/api/size-sample")
    public List<Size> sizeSamples() {
        return sizeService.getAll();
    }
}
