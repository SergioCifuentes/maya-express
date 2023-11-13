package com.mayaexpress.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WarehouseMovementDTO {

    @NotBlank(message = "shipmentId es obligatorio")
    private List<Integer> shipmentId;

    @NotBlank(message = "warehouseId es obligatorio")
    private Integer warehouseId;

    @NotBlank(message = "tripId es obligatorio")
    private Integer tripId;

    @NotBlank(message = "date es obligatorio")
    private Date date;

}
