package com.mayaexpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LightTripsDTO {
    private int tripId;
    private Float weight;
    private int vehicleId;
    private int vehicleMaxWeight;

}
