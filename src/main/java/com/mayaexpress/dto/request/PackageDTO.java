package com.mayaexpress.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackageDTO {

    @NotBlank(message = "weightLbs es obligatorio")
    private Double weightLbs;

    @NotBlank(message = "description es obligatorio")
    private String description;

    @NotBlank(message = "subTotal es obligatorio")
    private BigDecimal subTotal;
}
