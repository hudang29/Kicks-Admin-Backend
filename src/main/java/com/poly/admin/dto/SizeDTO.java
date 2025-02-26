package com.poly.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
    private Integer id;
    private String size;
    private Integer stock;

    SizeDTO(String size, Integer stock) {
        this.size = size;
        this.stock = stock;
    }
}
