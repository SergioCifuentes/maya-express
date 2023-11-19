package com.mayaexpress.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="Trip")
@Table(name = "TRIP", uniqueConstraints={
        @UniqueConstraint(columnNames = {"route_id", "date"})
})
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

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="date", nullable = false)
    private Date date;

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", currentWeight=" + currentWeight +
                ", route=" + route +
                ", date=" + date +
                '}';
    }
}
