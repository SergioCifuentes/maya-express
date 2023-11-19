package com.mayaexpress.service;

import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.entity.Vehicle;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final WarehouseService warehouseService;

    public VehicleService(VehicleRepository vehicleRepository, WarehouseService warehouseService) {
        this.vehicleRepository = vehicleRepository;
        this.warehouseService = warehouseService;
    }

    public Vehicle get(Integer id) {
        Optional<Vehicle> vehicleDB = vehicleRepository.findById(id);
        if (vehicleDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Vehicle not found.");
        }
        return vehicleDB.get();
    }

    public Vehicle assignVehicle(Integer idVehicle, Integer idBranch) {
        Warehouse warehouse = warehouseService.get(idBranch);
        Vehicle vehicle = get(idVehicle);
        vehicle.setWarehouse(warehouse);
        return vehicleRepository.save(vehicle);
    }
}
