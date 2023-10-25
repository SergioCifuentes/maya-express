package com.mayaexpress.repository;

import com.mayaexpress.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequestScope
public interface EmployeeRepository extends JpaRepository<Employee, BigDecimal> {

    Optional<Employee> findByUsername(String username);

    Employee getEmployeeByUsername(String username);

    Page<Employee> findAllByIsEnableIsTrue(Pageable pageable);

    Page<Employee> findAllByIsEnableIsFalse(Pageable pageable);
}
