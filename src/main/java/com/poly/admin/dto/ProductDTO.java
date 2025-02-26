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
    private Integer SupplierID;
    private String brand;
    private BigDecimal price;
    private String description;

    public ProductDTO(String name, Integer shoesCategoryID,
                      Integer GenderCategoryID, Integer SupplierID,
                      String brand, BigDecimal price, String description) {
        this.name = name;
        this.shoesCategoryID = shoesCategoryID;
        this.GenderCategoryID = GenderCategoryID;
        this.SupplierID = SupplierID;
        this.brand = brand;
        this.price = price;
        this.description = description;
    }
}
