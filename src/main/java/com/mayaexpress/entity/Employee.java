package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", scale = 0, precision = 13)
    private BigDecimal id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="last_name", nullable = false)
    private String lastName;


    @Column(name="hours_x_day",nullable = false, scale = 1, precision = 2)
    private BigDecimal hoursPerDay;

    @ColumnDefault("true")
    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;

    @Column(name="role")
    private Role role;

    @ManyToOne
    private Position position;

    @ManyToOne
    private Warehouse warehouse;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hoursPerDay=" + hoursPerDay +
                ", role=" + role +
                ", position=" + position +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
