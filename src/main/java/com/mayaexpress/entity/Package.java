package com.mayaexpress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity(name="Package")
@Table(name="PACKAGE")
@AllArgsConstructor
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="weight_lbs", nullable = false)
    private Double weightLbs;

    @Column(name="description", nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Shipment shipment;

    @Column(name="sub_total", scale = 2, precision = 5)
    private BigDecimal subTotal;


}
