package com.mayaexpress.entity;

import jakarta.persistence.*;

@Entity(name="Vehicle")
@Table(name = "VEHICLE")
public class Vehicle {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="plate", unique = true, nullable = false)
    private String plate;

    @Column(name="vehicle_type")
    private VehiculeType vehicleType;

    @ManyToOne(optional = true)
    private Shipment shipment;

    @Column(name = "max_weight")
    private Integer maxWeight;




}
