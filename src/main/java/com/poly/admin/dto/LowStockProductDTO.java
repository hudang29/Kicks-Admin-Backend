package com.poly.admin.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LowStockProductDTO {
    private Integer productDetailId;
    private String productName;
    private BigDecimal productPrice;
    private String productColor;
    private String productSize;
    private String productImage;
    private Long totalStock;
}
