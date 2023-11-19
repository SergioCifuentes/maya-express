package com.mayaexpress.repository;

import com.mayaexpress.entity.Wage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WageRepository extends JpaRepository<Wage, Integer> {
}
