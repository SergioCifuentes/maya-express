package com.mayaexpress.repository;

import com.mayaexpress.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DestinationRepository extends JpaRepository<Department, Integer> {


}
