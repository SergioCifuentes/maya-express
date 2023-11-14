package com.mayaexpress.repository;

import com.mayaexpress.dto.response.ShipmentHistoryDTO;
import com.mayaexpress.entity.ShipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShipmentHistoryRepository extends JpaRepository<ShipmentHistory, Integer> {

    @Query("SELECT NEW com.mayaexpress.dto.response.ShipmentHistoryDTO(sh.date, sp.isPaid, sp.total) " +
            "FROM ShipmentHistory sh " +
            "INNER JOIN ShipmentPayment sp ON sh.shipment.id = sp.id " +
            "WHERE sh.shipment.id = :shipmentId " +
            "ORDER BY sh.date DESC " +
            "LIMIT 1")
    Optional<ShipmentHistoryDTO> findLatestShipmentPaymentDetails(@Param("shipmentId") Integer shipmentId);

    @Query("SELECT COUNT(d.region) AS shipments, d.region FROM (" +
            "SELECT DISTINCT wh.department.id AS deparment_id, wh.id AS warehouse_id " +
            "FROM ShipmentHistory sh " +
            "INNER JOIN sh.warehouse wh " +
            "WHERE sh.state = 0 AND sh.date BETWEEN " +
            "TO_DATE(:startDate, 'dd/MM/yyyy') AND TO_DATE(:endDate, 'dd/MM/yyyy')) AS warehouse_info " +
            "INNER JOIN Destination d ON d.id = warehouse_info.deparment_id " +
            "GROUP BY d.region " +
            "ORDER BY shipments DESC")
    List<Object[]> getMovementsByRegion(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    @Query("SELECT sh.shipment.id AS shipment_id, sh.state, sh.date, s.receiveWarehouse.id AS receive_warehouse_id " +
            "FROM ShipmentHistory sh " +
            "INNER JOIN sh.shipment s " +
            "INNER JOIN s.receiveWarehouse d " +
            "WHERE s.receiveWarehouse.id = :receiveWarehouseId " +
            "AND sh.date = (SELECT MAX(sh2.date) " +
            "FROM ShipmentHistory sh2 " +
            "WHERE sh2.shipment.id = sh.shipment.id) " +
            "ORDER BY sh.shipment.id")
    List<Object[]> findLatestShipmentHistoryByReceiveWarehouseId(
            @Param("receiveWarehouseId") Integer receiveWarehouseId);
}
