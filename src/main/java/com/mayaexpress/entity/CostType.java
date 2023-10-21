package com.mayaexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class CostType {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="is_permanent")
    private Boolean isPermanent;


}
