package com.mayaexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "WAGE")
public class Wage {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="per_hour", scale = 5, precision = 2)
    private BigDecimal perHour;


}