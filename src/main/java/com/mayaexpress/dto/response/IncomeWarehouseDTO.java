package com.mayaexpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeWarehouseDTO {

    private BigDecimal income;
    private Integer warehouseId;

    public IncomeWarehouseDTO(Object[] result) {
        this.income = (BigDecimal) result[0];
        this.warehouseId = (Integer) result[1];
    }
}
