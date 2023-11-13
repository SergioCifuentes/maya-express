package com.mayaexpress.repository;

import com.mayaexpress.dto.response.MostPopularDestinationDTO;
import com.mayaexpress.dto.response.ShipmentHistoryDTO;
import com.mayaexpress.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DestinationRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT COUNT(s.id) AS shipments, d.name AS department " +
            "FROM Destination d " +
            "INNER JOIN Shipment s ON s.receiveWarehouse.id = d.id " +
            "GROUP BY d.id " +
            "ORDER BY shipments DESC")
    List<Object[]> findMostPopularDestinations();
}
