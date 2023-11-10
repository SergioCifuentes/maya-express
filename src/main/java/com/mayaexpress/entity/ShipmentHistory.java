package com.mayaexpress.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "ShipmentHistory")
@Table(name="SHIPMENT_HISTORY")
public class ShipmentHistory {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="is_arrival")
    private Boolean isArrival;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="warehouse")
    private Warehouse warehouse;

    @ManyToOne
    private Trip trip;
}
