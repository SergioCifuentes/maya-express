package com.mayaexpress.dto.response;

import com.mayaexpress.entity.HistoryState;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuideHistoryDTO {

    private Integer shipmentId;
    private HistoryState state;
    private Date date;
    private Integer receiveWarehouseId;

    public GuideHistoryDTO(Object[] result) {
        this.shipmentId = (Integer) result[0];
        this.state = (HistoryState) result[1];
        this.date = (Date) result[2];
        this.receiveWarehouseId = (Integer) result[3];
    }
}
