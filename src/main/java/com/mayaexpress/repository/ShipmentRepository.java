package com.mayaexpress.repository;

import com.mayaexpress.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {
}
