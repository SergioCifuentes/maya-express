package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="Route")
@Table(name = "ROUTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Warehouse destination;

    @Column(name="departure_day",nullable = false)
    private DayOfTheWeek departureDay;

    @Column(name="return_day",nullable = false)
    private DayOfTheWeek returnDay;

    @Column(name="hours",nullable = false)
    private Integer hours;

    @Column(name="departure_time",nullable = false)
    @Temporal(TemporalType.TIME)
    private Date departureTime;

    @Column(name="return_time",nullable = false)
    @Temporal(TemporalType.TIME)
    private Date returnTime;





}
