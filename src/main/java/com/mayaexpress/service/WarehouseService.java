package com.mayaexpress.service;

import com.mayaexpress.entity.Department;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.DestinationRepository;
import com.mayaexpress.repository.TripRepository;
import com.mayaexpress.repository.WarehouseRepository;
import com.mayaexpress.util.MergeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final MergeEntity<Warehouse> merge;
    private final DestinationRepository destinationRepository;

    private final TripRepository tripRepository;


    public WarehouseService(WarehouseRepository warehouseRepository, DestinationRepository destinationRepository,TripRepository tripRepository){
        this.warehouseRepository = warehouseRepository;
        this.merge = new MergeEntity<>();
        this.destinationRepository = destinationRepository;
        this.tripRepository=tripRepository;
    }

    public void create(Warehouse warehouse) {
        if (warehouse.getId() != null){
            Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouse.getId());
            if (warehouseOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"ID Already Exists");
        }
        Optional<Department> department = destinationRepository.findById(warehouse.getDepartment().getId());
        if (department.isEmpty()) throw new APIException(HttpStatus.NOT_FOUND, "Destination not found.");
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
        warehouse.get().setIsEnable(false);
        warehouseRepository.save(warehouse.get());
    }

    public Warehouse get(Integer id) {
        Optional<Warehouse> warehouseDB = warehouseRepository.findById(id);
        if (warehouseDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Warehouse not found.");
        }
        return warehouseDB.get();
    }

    public List<Warehouse> getBranches(){
        return warehouseRepository.findAllByIsBranchIsTrue();
    }

    public Warehouse getBranch(Integer id){
        Optional<Warehouse> warehouseOptional= warehouseRepository.findById(id);
        if(warehouseOptional.isEmpty()){
            throw new ResourceNotFoundException("Branch","ID",id);
        }
        if (!warehouseOptional.get().getIsBranch()){
            throw new APIException(HttpStatus.NOT_FOUND,"ID is not a Branch");
        }
        return warehouseOptional.get();
    }

    public ResponseEntity getTrips(Integer id) {
        Optional<Warehouse> warehouseOptional= warehouseRepository.findById(id);
        if(warehouseOptional.isEmpty()){
            throw new ResourceNotFoundException("Warehouse","ID",id);
        }
        return ResponseEntity.ok().body(tripRepository.getTripsByWarehouse(id));

    }
}
