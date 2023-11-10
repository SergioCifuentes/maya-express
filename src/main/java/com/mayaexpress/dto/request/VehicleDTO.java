package com.mayaexpress.dto.request;

import com.mayaexpress.entity.VehicleType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehicleDTO {

    private Integer id;

    @NotBlank(message = "plate es obligatorio")
    private String plate;

    @NotBlank(message = "vehiculeType es obligatorio")
    private VehicleType vehicleType;

    private Integer branchId;

    @NotBlank(message = "maxWeight es obligatorio")
    private Integer maxWeight;




}
