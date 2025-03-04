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
    private String color;
    private Integer discountId;
    private Boolean isDefault;
    //private BigDecimal salePrice;

    public ProductDetailDTO(String color, Integer productId) {
        this.color = color;
        this.productId = productId;
    }

    public ProductDetailDTO(Integer productId, Boolean isDefault) {
        this.productId = productId;
        this.isDefault = isDefault;
    }
}
