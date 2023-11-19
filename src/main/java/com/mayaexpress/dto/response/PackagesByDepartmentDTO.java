package com.mayaexpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackagesByDepartmentDTO {

    private Long packages;
    private String department;

    public PackagesByDepartmentDTO(Object[] result) {
        this.packages = (Long) result[0];
        this.department = (String) result[1];
    }
}
