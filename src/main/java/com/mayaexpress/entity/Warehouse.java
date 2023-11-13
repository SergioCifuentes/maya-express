package com.mayaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="WAREHOUSE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "square_meters", nullable = false)
    private Integer squareMeters;

    @Column(name = "max_weight_lbs", nullable = false)
    private Integer maxWeightLbs;

    @ColumnDefault("true")
    @Column(name = "is_enable", nullable = false)
    private Boolean isEnable;

    @ColumnDefault("true")
    @Column(name = "is_branch", nullable = false)
    private Boolean isBranch;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Department department;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", squareMeters=" + squareMeters +
                ", maxWeightLbs=" + maxWeightLbs +
                ", isEnable=" + isEnable +
                ", isBranch=" + isBranch +
                ", department=" + department +
                '}';
    }
}
