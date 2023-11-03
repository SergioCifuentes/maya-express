package com.mayaexpress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity(name="Warehouse")
@Table(name = "WAREHOUSE")
@Getter
@Setter

public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "square_meters", nullable = false)
    private Integer squareMeters;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_weight_lbs", nullable = false)
    private Integer maxWeightLbs;

    @ColumnDefault("true")
    @Column(name = "is_enable", nullable = false)
    private boolean isEnable;

    @JsonIgnore
    @OneToOne(mappedBy = "warehouse")
    private Branch branch;
}
