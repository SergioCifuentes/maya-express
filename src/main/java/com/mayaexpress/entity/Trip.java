package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="Trip")
@Table(name = "TRIP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="current_weight", nullable = false)
    private Float currentWeight;

    @ManyToOne
    private Route route;

    @Column(name="date", nullable = false)
    private Date date;



}
