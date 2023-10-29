package com.mayaexpress.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeAssignmentDTO {

    private Integer branch;
    private BigDecimal employee;
}
