package com.mayaexpress.entity;

import jakarta.persistence.*;

@Entity(name="Destination")
@Table(name="DESTINATION")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="region", nullable = false)
    private String region;
}
