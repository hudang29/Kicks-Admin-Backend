package com.poly.admin.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private Integer id;
    private Integer productId;
    private String name;
    private String color;
    private Integer discountId;
    //private BigDecimal salePrice;

    ProductDetailDTO(String color, Integer productId) {
        this.color = color;
        this.productId = productId;
    }
}
