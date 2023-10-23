package com.mayaexpress.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="BRANCH")
public class Branch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="warehouse_id", referencedColumnName="id", nullable=true)
    private Warehouse warehouse;

    @Column(name="vehicule_day")
    private DayOfTheWeek vehicleDay;

}
