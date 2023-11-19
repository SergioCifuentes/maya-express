package com.mayaexpress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name="ShipmentPayment")
@Table(name="SHIPMENT_PAYMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @JsonIgnore
    @OneToOne(mappedBy = "shipmentPayment")
    private Shipment shipment;

    @Column(name="is_paided", nullable = false)
    private Boolean isPaid;

    @Column(name="total", nullable = false, precision = 5, scale = 2)
    private BigDecimal total;

    @Column(name="pay_date")
    private Date date;
}
