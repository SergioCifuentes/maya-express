package com.mayaexpress.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackagesByRegionDTO {

    private Long packages;
    private String region;

    public PackagesByRegionDTO(Object[] result) {
        this.packages = (Long) result[0];
        this.region = (String) result[1];
    }
}
