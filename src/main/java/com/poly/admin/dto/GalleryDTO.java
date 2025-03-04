package com.poly.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDTO {
    private Integer id;
    private String image;
    private Integer productDetailID;
    private Boolean isDefault;

    public GalleryDTO(String image, Integer productDetailID) {
        this.image = image;
        this.productDetailID = productDetailID;
    }
}
