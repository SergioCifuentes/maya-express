package com.mayaexpress.repository;

import com.mayaexpress.entity.Employee;
import com.mayaexpress.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Page<Warehouse> findAllByIsEnableIsTrue(Pageable pageable);
    Page<Warehouse> findAllByIsEnableIsFalse(Pageable pageable);
}
