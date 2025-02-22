package com.poly.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoesCategoryDTO {
    private Integer id;
    private Integer GenderCategoryID;
    private String name;
}
