package com.mayaexpress.repository;

import com.mayaexpress.entity.CostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostTypeRepository extends JpaRepository<CostType, Integer> {

}
