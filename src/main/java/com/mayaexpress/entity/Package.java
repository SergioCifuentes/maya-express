package com.mayaexpress.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name="Package")
@Table(name="PACKAGE")
public class Package {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="weight_lbs", nullable = false)
    private Double weightLbs;

    @Column(name="description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    private Shipment shipment;

    @Column(name="sub_total", scale = 5, precision = 2)
    private BigDecimal subTotal;


}
