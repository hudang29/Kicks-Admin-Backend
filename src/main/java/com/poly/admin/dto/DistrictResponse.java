package com.poly.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class DistrictResponse {
    private String name;
    private List<Ward> wards;

}
