package com.mayaexpress.repository;

import com.mayaexpress.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    @Query("SELECT COUNT(p) FROM Package p " +
            "INNER JOIN p.shipment s " +
            "WHERE s.sendingWarehouse.id = :warehouseId " +
            "AND s.sendDate BETWEEN TO_DATE(:startDate, 'DD/MM/YYYY') AND TO_DATE(:endDate, 'DD/MM/YYYY')")
    Long countPackagesByWarehouseAndDateRange(
            @Param("warehouseId") Integer warehouseId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
