package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity(name="Shipment")
@Table(name="SHIPMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    private Branch sendingBranch;


    @Column(name="client_sending_name", nullable = false)
    private String clientSendingName;

    @Column(name="client_receiving_name", nullable = false)
    private String clientReceiveName;


    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "address")
    private String address;

    @ManyToOne
    private Branch receiveBranch;


    @OneToMany(mappedBy = "shipment")
    private Set<Package> packages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shipmentPayment", referencedColumnName="id", nullable=true)
    private ShipmentPayment shipmentPayment;


}
