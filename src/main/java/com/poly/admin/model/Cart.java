package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "User_ID")
    private Customer user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Product_ID")
    private Product product;

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "\"Size\"", nullable = false, length = 10)
    private String size;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "Color", nullable = false, length = 50)
    private String color;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

}