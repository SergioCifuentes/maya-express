package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "COST_TYPE")
@Table(name="CostType")
@Getter
@Setter
public class CostType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="is_permanent")
    private Boolean isPermanent;


}
