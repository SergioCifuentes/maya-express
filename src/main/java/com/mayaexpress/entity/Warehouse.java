package com.mayaexpress.entity;

import jakarta.persistence.*;

@Entity(name="Warehouse")
@Table(name = "WAREHOUSE")
public class Warehouse {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name = "square_meters", nullable = false)
    private Integer squareMeters;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_weight_lbs", nullable = false)
    private Integer maxWeightLbs;

    @OneToOne(mappedBy = "warehouse")
    private Branch branch;



}
