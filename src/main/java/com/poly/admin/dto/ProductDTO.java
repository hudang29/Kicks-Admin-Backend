package com.poly.admin.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer shoesCategoryID;
    private Integer GenderCategoryID;
    private String brand;
    private BigDecimal price;
    private String description;
}
