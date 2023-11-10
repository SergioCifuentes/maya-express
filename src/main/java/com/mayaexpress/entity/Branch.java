package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="BRANCH")
@Getter
@Setter
public class Branch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="warehouse_id", referencedColumnName="id", nullable=true)
    private Warehouse warehouse;


    public Branch(Integer id, String address, Warehouse warehouse) {
        this.id = id;
        this.address = address;
        this.warehouse = warehouse;
    }

    public Branch() {

    }
}
