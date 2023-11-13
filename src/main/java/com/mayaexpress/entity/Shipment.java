package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Warehouse sendingWarehouse;


    @Column(name="client_sending_name", nullable = false)
    private String clientSendingName;

    @Column(name="client_receiving_name", nullable = false)
    private String clientReceiveName;


    @Column(name = "send_date",nullable = false)
    private Date sendDate;

    @Column(name = "address",nullable = false)
    private String address;

    @ManyToOne()
    private Warehouse receiveWarehouse;


    @OneToMany(mappedBy = "shipment")
    private Set<Package> packages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shipmentPayment", referencedColumnName="id", nullable=true)
    private ShipmentPayment shipmentPayment;

    @Column(name = "expected_date")
    private Date expectedDate;

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", sendingWarehouse=" + sendingWarehouse +
                ", clientSendingName='" + clientSendingName + '\'' +
                ", clientReceiveName='" + clientReceiveName + '\'' +
                ", sendDate=" + sendDate +
                ", address='" + address + '\'' +
                ", receiveWarehouse=" + receiveWarehouse +
                ", packages=" + packages +
                ", shipmentPayment=" + shipmentPayment +
                ", expectedDate=" + expectedDate +
                '}';
    }
}
