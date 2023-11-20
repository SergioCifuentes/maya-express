package com.mayaexpress.repository;

import com.mayaexpress.dto.response.TripByWarehouse;
import com.mayaexpress.entity.Trip;
import com.mayaexpress.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Query(value="SELECT new com.mayaexpress.dto.response.TripByWarehouse(" +
            "t.id, r.id, t.currentWeight, r.awayWarehouse, t.date,r.isDeparture) FROM Trip t JOIN Route r ON t.route=r " +
            "WHERE r.awayWarehouse=:warehouse OR r.homeWarehouse=:warehouse")
    List<TripByWarehouse> getTripsByWarehouse(@Param("warehouse") Warehouse warehouse);

    List<Trip> getTripsByDateAfterAndDateBefore(Date dateAfter, Date before);

    @Query(value = "SELECT t FROM Trip t INNER JOIN ShipmentTrip st ON st.trip=t WHERE st.shipment.id=:id")
    List<Trip> getTripsByShipment(@Param("id") Integer id);
}
