package com.mayaexpress.repository;

import com.mayaexpress.dto.response.PackagesByRegionDTO;
import com.mayaexpress.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM get_package_counts(:is_origin, :start_date_str, :end_date_str)")
    List<Object[]> getPackageCounts(@Param("is_origin") Boolean isOrigin, @Param("start_date_str") String startDate, @Param("end_date_str") String endDate);

    @Query(nativeQuery = true, value = "SELECT * FROM get_package_counts_by_destination(:is_origin, :start_date_str, :end_date_str)")
    List<Object[]> get_package_counts_by_destination(@Param("is_origin") Boolean isOrigin, @Param("start_date_str") String startDate, @Param("end_date_str") String endDate);
}
