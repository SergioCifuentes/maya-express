package com.mayaexpress.service;

import com.mayaexpress.dto.request.VehicleDTO;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.entity.Vehicle;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.VehicleRepository;
import com.mayaexpress.repository.WarehouseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {

    private final WarehouseRepository warehouseRepository;

    private final VehicleRepository vehicleRepository;

    public ManagementService(WarehouseRepository warehouseRepository, VehicleRepository vehicleRepository) {
        this.warehouseRepository = warehouseRepository;
        this.vehicleRepository = vehicleRepository;
    }



    public Vehicle createVehicle(VehicleDTO vehicleDTO){
        if(vehicleDTO.getId()!=null){
            Optional<Vehicle> vehicleOptional= vehicleRepository.findById(vehicleDTO.getId());
            if(vehicleOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"ID Already Exists");
        }
        Vehicle vehicle;
        if(vehicleDTO.getWarehouseId()!=null){
            Optional<Warehouse> warehouseOptional=warehouseRepository.findById(vehicleDTO.getWarehouseId());
            if(warehouseOptional.isEmpty()) throw new ResourceNotFoundException("Branch","ID",vehicleDTO.getWarehouseId());
            vehicle= new Vehicle(vehicleDTO.getId(),vehicleDTO.getPlate(),vehicleDTO.getVehicleType(),warehouseOptional.get(),vehicleDTO.getMaxWeight());
        }else {
            vehicle= new Vehicle(vehicleDTO.getId(),vehicleDTO.getPlate(),vehicleDTO.getVehicleType(),null,vehicleDTO.getMaxWeight());
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicle(Integer id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isEmpty()) throw new ResourceNotFoundException("Vehicle","id",id);
        return vehicle.get();
    }
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Vehicle updateVehicle(Vehicle vehicle, Integer id){
        Optional<Vehicle> opOldVehicle = vehicleRepository.findById(id);
        if (opOldVehicle.isEmpty()) throw new ResourceNotFoundException("Vehicle","id",id);
        Vehicle oldVehicle = opOldVehicle.get();
        if(vehicle.getPlate()!=null) oldVehicle.setPlate(vehicle.getPlate());
        if(vehicle.getVehicleType()!=null) oldVehicle.setVehicleType(vehicle.getVehicleType());
        if(vehicle.getMaxWeight()!=null) oldVehicle.setMaxWeight(vehicle.getMaxWeight());
        return vehicleRepository.save(oldVehicle);
    }

    public void deleteVehicle(Integer id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isEmpty()) throw new ResourceNotFoundException("Vehicle","id",id);
        vehicleRepository.delete(vehicle.get());
    }
}
