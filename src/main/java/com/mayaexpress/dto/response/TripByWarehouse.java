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



}
