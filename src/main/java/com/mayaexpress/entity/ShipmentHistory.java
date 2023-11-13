package com.mayaexpress.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "ShipmentHistory")
@Table(name="SHIPMENT_HISTORY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShipmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Shipment shipment;

    @Column(name="state",nullable = false)
    private HistoryState state;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm a")
    private Date date;

    @ManyToOne
    @JoinColumn(name="warehouse")
    @JsonIgnore
    private Warehouse warehouse;

    @ManyToOne
    @JsonIgnore
    private Trip trip;


}
