package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "\"Size\"", nullable = false, length = 10)
    private String size;

    @ColumnDefault("0")
    @Column(name = "Stock")
    private Integer stock;

}