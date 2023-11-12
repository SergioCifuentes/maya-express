package com.mayaexpress.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReceiveDTO {

    @NotBlank(message = "shipmentId es obligatorio")
    private Integer shipmentId;

    @NotBlank(message = "warehouseId es obligatorio")
    private Integer warehouseId;


}
