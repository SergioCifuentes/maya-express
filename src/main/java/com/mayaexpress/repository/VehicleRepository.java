package com.mayaexpress.repository;

import com.mayaexpress.entity.Vehicle;
import com.mayaexpress.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Repository
@RequestScope
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findAllByWarehouse_Id(Integer warehouse_id);
}
