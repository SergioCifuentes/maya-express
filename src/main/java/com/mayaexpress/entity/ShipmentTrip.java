package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="ShipmentTrip")
@Table(name = "SHIPMENT_TRIP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentTrip {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Shipment shipment;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    private Trip trip;
}
