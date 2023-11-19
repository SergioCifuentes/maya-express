package com.mayaexpress.repository;

import com.mayaexpress.dto.response.TripByWarehouse;
import com.mayaexpress.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Query(value="SELECT new com.mayaexpress.dto.response.TripByWarehouse(" +
            "t.id, r.id, t.currentWeight, r.awayWarehouse, t.date,r.isDeparture) FROM Trip t JOIN Route r ON t.route=r ")
    List<TripByWarehouse> getTripsByWarehouse(Integer warehouse);
}
