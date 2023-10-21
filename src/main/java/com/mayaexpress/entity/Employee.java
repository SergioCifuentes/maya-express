package com.mayaexpress.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "id", scale = 13, precision = 0)
    private BigDecimal id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="hours_x_day",nullable = false, scale = 2, precision = 1)
    private BigDecimal hoursPerDay;


    @Column(name="role")
    private Role role;

    @ManyToOne
    private Position position;


    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;
}
