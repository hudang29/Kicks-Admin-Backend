package com.poly.admin.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
public class BestSellerDTO {
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private String productColor;
    private String productImage;
    private Long totalSold;
}
