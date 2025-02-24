package com.poly.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
public class DiscountAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Type", length = 50)
    private String type;

    @ColumnDefault("getdate()")
    @Column(name = "\"Time_stamp\"")
    private Instant timestamp;

    @Nationalized
    @Lob
    @Column(name = "Old_data")
    private String oldData;

    @Nationalized
    @Lob
    @Column(name = "New_data")
    private String newData;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Edit_By")
    private String editBy;

}