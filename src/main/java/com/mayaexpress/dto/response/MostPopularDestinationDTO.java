package com.mayaexpress.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MostPopularDestinationDTO {

    private Long shipments;
    private String department;

    public MostPopularDestinationDTO(Object[] result) {
        this.shipments = (Long) result[0];
        this.department = (String) result[1];
    }
}
