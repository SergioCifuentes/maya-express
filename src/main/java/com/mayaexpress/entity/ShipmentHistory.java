package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "ShipmentHistory")
@Table(name="SHIPMENT_HISTORY")
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentHistory {

    @Id
    @Column(name="id")
    private Integer id;

    @ManyToOne
    private Shipment shipment;

    @Column(name="state",nullable = false)
    private HistoryState state;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="warehouse")
    private Warehouse warehouse;

    @ManyToOne
    private Trip trip;


}
