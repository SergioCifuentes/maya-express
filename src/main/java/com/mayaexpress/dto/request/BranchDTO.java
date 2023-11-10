package com.mayaexpress.dto.request;

import com.mayaexpress.entity.DayOfTheWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BranchDTO {

    private Integer id;

    @NotBlank(message = "address es obligatorio")
    private String address;

    private Integer warehouse;

}
