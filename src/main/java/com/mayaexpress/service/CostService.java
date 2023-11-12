package com.mayaexpress.service;

import com.mayaexpress.entity.Cost;
import com.mayaexpress.entity.CostType;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.ResourceNotFoundException;

import com.mayaexpress.repository.CostRepository;
import com.mayaexpress.repository.CostTypeRepository;
import com.mayaexpress.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostService {

    private final CostRepository costRepository;

    private final CostTypeRepository costTypeRepository;

    private final WarehouseRepository warehouseRepository;

    public CostService(CostRepository costRepository, CostTypeRepository costTypeRepository, WarehouseRepository warehouseRepository) {
        this.costRepository = costRepository;
        this.costTypeRepository = costTypeRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Page<CostType> getAllCostType(int page, int size, boolean pagination) {
        return  costTypeRepository.findAll(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Cost addCost(Cost cost) {
        Optional<CostType> costType = costTypeRepository.findById(cost.getCostType().getId());
        if(costType.isEmpty()) throw new ResourceNotFoundException("CostType", "ID", cost.getCostType().getId());
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(cost.getWarehouse().getId());
        if(warehouseOptional.isEmpty()) throw new ResourceNotFoundException("Warehouse", "ID", cost.getCostType().getId());
        return costRepository.save(cost);
    }
}
