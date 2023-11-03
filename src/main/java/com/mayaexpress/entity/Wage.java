package com.mayaexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "WAGE")
@Getter
public class Wage {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="per_hour", scale = 2, precision = 8)
    private BigDecimal perHour;


}
