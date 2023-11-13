package com.mayaexpress.repository;

import com.mayaexpress.dto.response.ShipmentHistoryDTO;
import com.mayaexpress.entity.ShipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShipmentHistoryRepository extends JpaRepository<ShipmentHistory, Integer> {

    @Query("SELECT NEW com.mayaexpress.dto.response.ShipmentHistoryDTO(sh.date, sp.isPaid, sp.total) " +
            "FROM ShipmentHistory sh " +
            "INNER JOIN ShipmentPayment sp ON sh.shipment.id = sp.id " +
            "WHERE sh.shipment.id = :shipmentId " +
            "ORDER BY sh.date DESC " +
            "LIMIT 1")
    Optional<ShipmentHistoryDTO> findLatestShipmentPaymentDetails(@Param("shipmentId") Integer shipmentId);
}
