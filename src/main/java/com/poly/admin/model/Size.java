package com.poly.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"Size\"")
public class Size {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"Size\"", nullable = false)
    private String size;

}