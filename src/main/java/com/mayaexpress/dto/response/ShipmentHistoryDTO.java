package com.mayaexpress.dto.response;

import com.mayaexpress.entity.Package;
import com.mayaexpress.entity.Trip;
import com.mayaexpress.entity.Warehouse;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private List<Trip> trips;
    private List<Package> packages;

    public ShipmentHistoryDTO(Date date, Boolean isPaid, BigDecimal total, Warehouse warehouse) {
        this.date = date;
        this.isPaid = isPaid;
        this.total = total;
        this.warehouse = warehouse;
    }
}
