package com.mayaexpress.dto.response;

import com.mayaexpress.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    private Warehouse warehouse;
    private BigDecimal balance;
    private Integer employees;
    private Integer vehicles;
}
