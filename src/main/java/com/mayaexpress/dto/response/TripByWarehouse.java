package com.mayaexpress.dto.response;

import com.mayaexpress.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripByWarehouse {
    private Integer tripId;
    private Integer vehicleId;
    private Float currentWeight;
    private Warehouse warehouseToOrFrom;
    private Date date;
    private Boolean isDeparture;

    public TripByWarehouse(Integer tripId, Integer vehicleId, Float currentWeight, Warehouse warehouseToOrFrom, Date date, Boolean isDeparture, Boolean isHome) {
        this.tripId = tripId;
        this.vehicleId = vehicleId;
        this.currentWeight = currentWeight;
        this.warehouseToOrFrom = warehouseToOrFrom;
        this.date = date;
        this.isDeparture = !(isDeparture^isHome);
    }
}
