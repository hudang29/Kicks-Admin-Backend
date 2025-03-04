package com.poly.admin.service;

import com.poly.admin.dto.DistrictResponse;
import com.poly.admin.dto.Ward;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String getCityNameByCode(String cityCode) {
        String url = "https://provinces.open-api.vn/api/p/" + cityCode;
        try {
            LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
            return response != null ? response.getName() : "Unknown City";
        } catch (Exception e) {
            return "Unknown City";
        }
    }

    public String getDistrictNameByCode(String districtCode) {
        String url = "https://provinces.open-api.vn/api/d/" + districtCode;
        try {
            LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
            return response != null ? response.getName() : "Unknown District";
        } catch (Exception e) {
            return "Unknown District";
        }
    }

    public String getWardNameByCode(String wardCode, String districtCode) {
        String url = "https://provinces.open-api.vn/api/d/" + districtCode + "?depth=2";
        try {
            DistrictResponse response = restTemplate.getForObject(url, DistrictResponse.class);
            if (response != null && response.getWards() != null) {
                for (Ward ward : response.getWards()) {
                    if (ward.getCode().equals(wardCode)) {
                        return ward.getName();
                    }
                }
            }
            return "Unknown Ward (Not Found)";
        } catch (Exception e) {
            return "Unknown Ward (Error)";
        }
    }


    // Class này ánh xạ JSON từ API vào Java Object
    private static class LocationResponse {
        private String name;

        public String getName() {
            return name;
        }
    }
}
