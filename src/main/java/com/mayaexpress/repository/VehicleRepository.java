package com.mayaexpress.repository;

import com.mayaexpress.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

@Repository
@RequestScope
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
