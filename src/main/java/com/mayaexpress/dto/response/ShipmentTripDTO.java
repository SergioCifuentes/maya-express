package com.mayaexpress.dto.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentTripDTO {

    private Integer idTrip;
    private List<Integer> shipments;
}
