package com.mayaexpress.entity;

import jakarta.persistence.*;

@Entity(name = "Position")
@Table(name = "POSITION")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne
    private Wage wage;

}
