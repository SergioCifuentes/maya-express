package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Vehicle")
@Table(name = "VEHICLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="plate", unique = true, nullable = false)
    private String plate;

    @Column(name="vehicle_type")
    private VehicleType vehicleType;

    @ManyToOne
    private Warehouse warehouse;

    @Column(name = "max_weight")
    private Integer maxWeight;



}
