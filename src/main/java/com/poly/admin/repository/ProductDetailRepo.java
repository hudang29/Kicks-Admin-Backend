package com.poly.admin.repository;

import com.poly.admin.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, Integer> {

    List<ProductDetail> findAll();

    List<ProductDetail> findByProductId(Integer productId);

    @Query("""
            SELECT d.color FROM ProductDetail d
            WHERE d.product.id = :productId
            """)
    List<String> findColorByProductId(@Param("productId") Integer productId);

}
