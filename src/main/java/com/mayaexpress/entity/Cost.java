package com.mayaexpress.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "Cost")
@Table(name="COST")
public class Cost {

    @Id
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    @Column(name = "date")
    private Date date;

    @Column(name="description")
    private String description;

}
