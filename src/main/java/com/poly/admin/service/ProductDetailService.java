package com.poly.admin.service;

import com.poly.admin.dto.ProductDetailDTO;
import com.poly.admin.model.Product;
import com.poly.admin.model.ProductDetail;
import com.poly.admin.model.ProductDiscount;
import com.poly.admin.repository.DiscountRepo;
import com.poly.admin.repository.ProductDetailRepo;
import com.poly.admin.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DiscountRepo discountRepo;

    public List<String> getColorByProductId(int id) {
        return productDetailRepo.findColorByProductId(id);
    }


    public List<ProductDetailDTO> getDetailByProductId(int id) {
        return productDetailRepo.findByProductId(id)
                .stream()
                .map(productDetail -> new ProductDetailDTO(
                        productDetail.getId(),
                        productDetail.getProduct().getId(),
                        productDetail.getColor(),
                        productDetail.getProductDiscount() != null ?
                                productDetail.getProductDiscount().getId() : null,
                        productDetail.getIsDefault()
                )).toList();
    }

    public Optional<ProductDetailDTO> getDetailById(int id) {
        return productDetailRepo.findById(id).map(productDetail -> new ProductDetailDTO(
                productDetail.getId(),
                productDetail.getProduct().getId(),
                productDetail.getColor(),
                productDetail.getProductDiscount() != null ?
                        productDetail.getProductDiscount().getId() : null,
                productDetail.getIsDefault()
        ));
    }

    public ProductDetail updateProductDetail(ProductDetailDTO productDetailDTO) {
        Integer productId = productDetailDTO.getProductId();
        Integer discountId = productDetailDTO.getDiscountId();
        Integer productDetailId = productDetailDTO.getId();

        ProductDetail productDetail;
        if (productDetailId != null) {
            productDetail = productDetailRepo.findById(productDetailId)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại!"));
        } else {
            productDetail = new ProductDetail();
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is null"));
        productDetail.setProduct(product);
        productDetail.setColor(productDetailDTO.getColor());

        Optional<ProductDiscount> optionalDiscount = discountRepo.findById(discountId);
        if (optionalDiscount.isPresent()) {
            productDetail.setProductDiscount(optionalDiscount.get());
        } else {
            productDetail.setProductDiscount(null);
        }
        //productDetail.setIsDefault(false);

        return productDetailRepo.save(productDetail);
    }

    public ProductDetail addProductDetail(ProductDetailDTO updateData) {
        Integer productId = updateData.getProductId();

        ProductDetail productDetail = new ProductDetail();

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is null"));
        productDetail.setProduct(product);
        productDetail.setColor(updateData.getColor());
        productDetail.setProductDiscount(discountRepo.findById(1).orElse(null));
        productDetailRepo.save(productDetail);
        return productDetailRepo.save(productDetail);
    }
}
