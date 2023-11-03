package com.mayaexpress.service;

import com.mayaexpress.entity.Employee;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.repository.WarehouseRepository;
import com.mayaexpress.util.MergeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final MergeEntity<Warehouse> merge;

    public WarehouseService(WarehouseRepository warehouseRepository){
        this.warehouseRepository = warehouseRepository;
        this.merge = new MergeEntity<>();
    }

    public void create(Warehouse warehouse) {
        if (warehouse.getId() != null){
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouse.getId());
            if (warehouseOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"ID Already Exists");
        }
        warehouseRepository.save(warehouse);
    }

    public Page<Warehouse> getAll(int page, int size, boolean pagination) {
        return  warehouseRepository.findAll(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Page<Warehouse> getAllActivates(int page, int size, boolean pagination){
        return warehouseRepository.findAllByIsEnableIsTrue(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Page<Warehouse> getAllDeactivates(int page, int size, boolean pagination){
        return warehouseRepository.findAllByIsEnableIsFalse(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Warehouse update(Warehouse warehouse, Integer id) {
        Optional<Warehouse> warehouseDB = warehouseRepository.findById(id);
        if (warehouseDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Warehouse not found.");
        }
        return warehouseRepository.save(merge.mergeEntities(warehouse, warehouseDB.get()));
    }

    public void delete(Integer id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if (warehouse.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Warehouse not found.");
        }
        warehouse.get().setEnable(false);
        warehouseRepository.save(warehouse.get());
    }

    public Warehouse get(Integer id) {
        Optional<Warehouse> warehouseDB = warehouseRepository.findById(id);
        if (warehouseDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Warehouse not found.");
        }
        return warehouseDB.get();
    }
}