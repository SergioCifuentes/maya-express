package com.mayaexpress.repository;

import com.mayaexpress.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Repository
@RequestScope
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Page<Warehouse> findAllByIsEnableIsTrue(Pageable pageable);
    Page<Warehouse> findAllByIsEnableIsFalse(Pageable pageable);

    List<Warehouse> findAllByIsBranchIsTrue();

    List<Warehouse> findAllByIsBranchIsTrueAndDepartment_Id(Integer department_id);
}
