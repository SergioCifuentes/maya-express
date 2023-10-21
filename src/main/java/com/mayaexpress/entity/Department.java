package com.mayaexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="Destination")
@Table(name="DESTINATION")
public class Department {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="region", nullable = false)
    private String region;
}
