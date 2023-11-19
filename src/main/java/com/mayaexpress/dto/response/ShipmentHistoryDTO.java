package com.mayaexpress.dto.response;

import com.mayaexpress.entity.Warehouse;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentHistoryDTO {

    private Date date;
    private Boolean isPaid;
    private BigDecimal total;
    private Warehouse warehouse;
}
