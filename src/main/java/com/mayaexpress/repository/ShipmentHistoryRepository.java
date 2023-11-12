package com.mayaexpress.repository;

import com.mayaexpress.entity.ShipmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentHistoryRepository extends JpaRepository<ShipmentHistory, Integer> {
}
