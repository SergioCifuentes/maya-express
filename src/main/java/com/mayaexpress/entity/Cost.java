package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Cost")
@Table(name="COST")
@Getter
@Setter
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Warehouse warehouse;

    @Column(name = "date")
    private Date date;

    @Column(name="description")
    private String description;

    @ManyToOne
    private CostType costType;

    @Column(name="amount", nullable = false, precision = 5, scale = 2)
    private BigDecimal amount;
}
