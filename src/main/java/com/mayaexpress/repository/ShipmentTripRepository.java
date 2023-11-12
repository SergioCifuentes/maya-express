package com.mayaexpress.repository;

import com.mayaexpress.entity.Shipment;
import com.mayaexpress.entity.ShipmentTrip;
import com.mayaexpress.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShipmentTripRepository extends JpaRepository<ShipmentTrip, Integer> {

    @Query(value = "SELECT st.trip FROM ShipmentTrip st WHERE st.shipment=:shipment AND st.number=:number")
    Trip getTrip(@Param("shipment") Shipment shipment, @Param("number") Integer number);

    @Query(value = "SELECT st.number FROM ShipmentTrip st WHERE st.shipment=:shipment AND st.trip=:trip")
    Integer getNumber(@Param("shipment") Shipment shipment, @Param("trip") Trip trip);
}
