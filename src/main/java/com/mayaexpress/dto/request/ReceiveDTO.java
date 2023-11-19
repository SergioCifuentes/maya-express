package com.mayaexpress.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class ReceiveDTO {

    @NotBlank(message = "shipmentId es obligatorio")
    private Integer shipmentId;

    @NotBlank(message = "warehouseId es obligatorio")
    private Integer warehouseId;

    @NotBlank(message = "date es obligatorio")
    private Date date;


}
