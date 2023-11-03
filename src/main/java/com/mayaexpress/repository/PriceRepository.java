package com.mayaexpress.repository;

import com.mayaexpress.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price,Integer> {

    @Query(value = "SELECT p FROM Price p WHERE p.origen.id=:origin AND p.destination.id=:destination")
    Optional<Price> findByOrigenAndDestination(@Param("origin") Integer origin,@Param("destination") Integer destination);
}
