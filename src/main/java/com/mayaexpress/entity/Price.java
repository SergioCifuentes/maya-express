package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="Price")
@Table(name = "PRICE")
@Getter
@Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="sending_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal sendingCost;

    @ManyToOne
    @JoinColumn(name="origin")
    private Department origen;

    @ManyToOne
    @JoinColumn(name="destination")
    private Department destination;

    @Column(name="cost_x_lb", nullable = false, precision = 5, scale = 2)
    private BigDecimal costPerLb;

}
