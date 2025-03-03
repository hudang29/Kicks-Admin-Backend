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
}
