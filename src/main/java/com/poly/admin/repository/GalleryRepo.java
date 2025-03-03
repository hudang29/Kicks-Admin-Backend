package com.poly.admin.repository;

import com.poly.admin.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryRepo extends JpaRepository<Gallery, Integer> {

    @Override
    Optional<Gallery> findById(Integer integer);

    List<Gallery> findByProductDetail_IdOrderByIsDefaultDesc(Integer id);

    @Query("""
             SELECT g.image FROM Gallery g
             JOIN ProductDetail d ON d.id = g.productDetail.id
             JOIN Product p ON p.id = d.product.id
             WHERE p.id = :id AND d.isDefault = true AND g.isDefault = true
            """)
    List<String> findImageIsDefaultByProductId(@Param("id") Integer id);

    @Query("""
             SELECT g.image FROM Gallery g
             JOIN ProductDetail d ON d.id = g.productDetail.id
             JOIN Product p ON p.id = d.product.id
             WHERE p.id = :id
             ORDER BY d.isDefault DESC, g.isDefault DESC
            """)
    List<String> findImageByProductId(@Param("id") Integer id);

    @Query("""
             SELECT g.image FROM Gallery g
             JOIN ProductDetail d ON d.id = g.productDetail.id
             WHERE d.id = :id AND g.isDefault = :isDefault
            """)
    List<String> findImageByProductDetailId(@Param("id") Integer id, @Param("isDefault") Boolean isDefault);
}
