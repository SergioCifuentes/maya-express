package com.mayaexpress.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovementsByRegionDTO {

    private Long shipments;
    private String region;

    public MovementsByRegionDTO(Object[] result) {
        this.shipments = (Long) result[0];
        this.region = (String) result[1];
    }
}
