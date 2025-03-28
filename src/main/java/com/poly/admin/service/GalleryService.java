package com.poly.admin.service;

import com.poly.admin.dto.GalleryDTO;
import com.poly.admin.model.Gallery;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.repository.GalleryRepo;
import com.poly.admin.repository.ProductDetailRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepo galleryRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;

    public List<GalleryDTO> getAllGalleryByProductDetailId(Integer productDetailId) {
        return galleryRepo.findByProductDetail_IdOrderByIsDefaultDesc(productDetailId)
                .stream()
                .map(gallery -> new GalleryDTO(
                        gallery.getId(),
                        gallery.getImage(),
                        gallery.getProductDetail().getId(),
                        gallery.getIsDefault()
                )).toList();
    }

    public String getGalleryByProductId(Integer productId) {
        List<String> imgList = galleryRepo.findImageIsDefaultByProductId(productId);

        if (imgList.isEmpty()) {
            imgList = galleryRepo.findImageByProductId(productId);
        }

        return imgList.isEmpty() ? null : imgList.getFirst();
    }

    public String getGalleryByProductDetailId(Integer productDetailId) {
        List<String> imgList = galleryRepo.findImageByProductDetailId(productDetailId, true);
        if (imgList.isEmpty()) {
            imgList = galleryRepo.findImageByProductDetailId(productDetailId, false);
        }
        return imgList.isEmpty() ? null : imgList.getFirst();
    }

    public GalleryDTO addGallery(GalleryDTO galleryDTO) {

        Integer productDetailId = galleryDTO.getProductDetailID();

        ProductDetail productDetail = productDetailRepo.findById(productDetailId).orElseThrow(
                () -> new EntityNotFoundException("Product detail not found"));
        Gallery gallery = new Gallery(productDetail, galleryDTO.getImage(), false);

        Gallery newGallery = galleryRepo.save(gallery);

        return new GalleryDTO(newGallery.getId(),
                newGallery.getImage(),
                newGallery.getProductDetail().getId(),
                newGallery.getIsDefault());
    }

}
