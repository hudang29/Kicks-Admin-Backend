package com.poly.admin.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
