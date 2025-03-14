package com.poly.admin.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Integer OrderDetailID;
    private Integer ProductDetailID;
    private String ProductName;
    private String ProductImage;
    private String size;
    private String color;
    private Integer quantity;
    private BigDecimal price;
}
