package com.mayaexpress.repository;

import com.mayaexpress.entity.ShipmentPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentPaymentRespository extends JpaRepository<ShipmentPayment, Integer> {
}
