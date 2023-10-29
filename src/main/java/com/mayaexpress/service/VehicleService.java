package com.mayaexpress.service;

import com.mayaexpress.entity.Branch;
import com.mayaexpress.entity.Employee;
import com.mayaexpress.entity.Vehicle;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BranchService branchService;

    public VehicleService(VehicleRepository vehicleRepository, BranchService branchService) {
        this.vehicleRepository = vehicleRepository;
        this.branchService = branchService;
    }

    public Vehicle get(Integer id) {
        Optional<Vehicle> vehicleDB = vehicleRepository.findById(id);
        if (vehicleDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Vehicle not found.");
        }
        return vehicleDB.get();
    }

    public Vehicle assignVehicle(Integer idVehicle, Integer idBranch) {
        Branch branch = branchService.get(idBranch);
        Vehicle vehicle = get(idVehicle);
        vehicle.setBranch(branch);
        return vehicleRepository.save(vehicle);
    }
}
