package com.mayaexpress.repository;

import com.mayaexpress.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, BigDecimal> {

    Optional<Employee> findByUsername(String username);

    Employee getEmployeeByUsername(String username);
}
