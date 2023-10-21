package com.mayaexpress.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity(name="Shipment")
@Table(name="SHIPMENT")
public class Shipment {
    @Id
    @Column(name="id")
    private Integer id;

    @ManyToOne
    private Branch sendingBranch;

    //tiempo espera

    @Column(name="client_sending_name", nullable = false)
    private String clientSendingName;

    @Column(name="client_receiving_name", nullable = false)
    private String clientReceiveName;

    @Column(name="is_paided", nullable = false)
    private Boolean isPaided;

    @Column(name="total", nullable = false, scale = 5, precision = 2)
    private BigDecimal total;

    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "address")
    private String address;

    @ManyToOne
    private Branch receiveBranch;

    @Column(name="pay_date")
    private Date payDate;

    @OneToMany(mappedBy = "shipment")
    private Set<Package> packages;

}
