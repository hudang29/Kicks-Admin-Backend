package com.poly.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "Product_size")
public class ProductSize {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Product_detail_ID")
    private ProductDetail productDetail;

    @Column(name = "\"Size\"")
    private Integer size;

    @Column(name = "Stock")
    private Integer stock;

}