package com.mayaexpress.repository;

import com.mayaexpress.dto.response.PackagesByRegionDTO;
import com.mayaexpress.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM get_package_counts(:is_origin, :start_date_str, :end_date_str)")
    List<Object[]> getPackageCounts(
            @Param("is_origin") Boolean isOrigin,
            @Param("start_date_str") String startDate,
            @Param("end_date_str") String endDate
    );

    @Query(nativeQuery = true, value = "SELECT * FROM get_package_counts_by_destination(:is_origin, :start_date_str, :end_date_str)")
    List<Object[]> get_package_counts_by_destination(
            @Param("is_origin") Boolean isOrigin,
            @Param("start_date_str") String startDate,
            @Param("end_date_str") String endDate
    );

    @Query(nativeQuery = true, value =
            "SELECT SUM(cost.amount) AS income, warehouse.id " +
                    "FROM shipment " +
                    "INNER JOIN warehouse ON warehouse.id = shipment.sending_warehouse_id " +
                    "INNER JOIN cost ON cost.branch = warehouse.id " +
                    "WHERE cost.date BETWEEN TO_DATE(:start_date, 'DD/MM/YYYY') AND TO_DATE(:end_date, 'DD/MM/YYYY') " +
                    "GROUP BY warehouse.id")
    List<Object[]> getExpensedByWarehouse(
            @Param("start_date") String startDate,
            @Param("end_date") String endDate
    );

    @Query(nativeQuery = true, value = "SELECT SUM(shipment_payment.total) as expensed FROM shipment " +
            "INNER JOIN shipment_payment ON shipment_payment.id = shipment.id " +
            "WHERE shipment_payment.is_paided = true " +
            "AND shipment_payment.pay_date BETWEEN TO_DATE(:start_date, 'DD/MM/YYYY') AND TO_DATE(:end_date, 'DD/MM/YYYY') " +
            "AND shipment.sending_warehouse_id = :warehouseId")
    BigDecimal getIncomeAmount(
            @Param("start_date") String startDate,
            @Param("end_date") String endDate,
            @Param("warehouseId") Integer warehouseId
    );

}
