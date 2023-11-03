package com.mayaexpress.repository;

import com.mayaexpress.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Integer > {
}
