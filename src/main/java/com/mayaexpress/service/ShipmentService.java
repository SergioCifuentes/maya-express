package com.mayaexpress.service;

import com.mayaexpress.dto.request.BranchDTO;
import com.mayaexpress.dto.request.VehicleDTO;
import com.mayaexpress.entity.*;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    private final DestinationRepository destinationRepository;

    private final PriceRepository priceRepository;

    private final BranchRepository branchRepository;

    private final WarehouseRepository warehouseRepository;

    private final VehicleRepository vehicleRepository;

    public ShipmentService(DestinationRepository destinationRepository, PriceRepository priceRepository,
                           BranchRepository branchRepository, WarehouseRepository warehouseRepository, VehicleRepository vehicleRepository) {
        this.destinationRepository = destinationRepository;
        this.priceRepository = priceRepository;
        this.branchRepository=branchRepository;
        this.warehouseRepository=warehouseRepository;
        this.vehicleRepository=vehicleRepository;
    }


    public List<Department> getDepartments(){
        return destinationRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }



    public Department getDepartment(Integer id){
        Optional<Department> department = destinationRepository.findById(id);
        if (department.isEmpty()) throw new ResourceNotFoundException("Department","id",id);
        return department.get();
    }

    public List<Price> getPrices(){
        return priceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Price getPrice(Integer id){
        Optional<Price> price = priceRepository.findById(id);
        if (price.isEmpty()) throw new ResourceNotFoundException("Price","id",id);
        return price.get();
    }

    public Price getPriceByDepartments(Integer idOrigin, Integer idDestination){
        Optional<Price> price = priceRepository.findByOrigenAndDestination(idOrigin,idDestination);
        if (price.isEmpty()) throw new ResourceNotFoundException("Price","idOrigin",idOrigin);
        return price.get();
    }

    public Price updatePrice(Price price, Integer id){
        Optional<Price> opOldPrice = priceRepository.findById(id);
        if (opOldPrice.isEmpty()) throw new ResourceNotFoundException("Price","id",id);
        Price oldPrice = opOldPrice.get();
        if(price.getCostPerLb()!=null)oldPrice.setCostPerLb(price.getCostPerLb());
        if(price.getSendingCost()!=null)oldPrice.setSendingCost(price.getSendingCost());
        return priceRepository.save(oldPrice);

    }

    public Branch createBranch(BranchDTO branchDTO){
        if(branchDTO.getId()!=null){

            Optional<Branch> branchOptional= branchRepository.findById(branchDTO.getId());
            if(branchOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"ID Already Exists");
        }
        Branch branch;
        if(branchDTO.getWarehouse()!=null){
            Optional<Warehouse> warehouseOptional=warehouseRepository.findById(branchDTO.getWarehouse());
            if(warehouseOptional.isEmpty()){
                throw new ResourceNotFoundException("Warehouse","ID",branchDTO.getWarehouse());
            }
            branch= new Branch(branchDTO.getId(),branchDTO.getAddress(),warehouseOptional.get(),branchDTO.getVehicleDay());
        }else {
            branch = new Branch(branchDTO.getId(), branchDTO.getAddress(), null, branchDTO.getVehicleDay());
        }
        return branchRepository.save(branch);
    }

    public Branch getBranch(Integer id){
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isEmpty()) throw new ResourceNotFoundException("Branch","id",id);
        return branch.get();
    }
    public List<Branch> getBranches(){
        return branchRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Branch updateBranch(Branch branch, Integer id){
        Optional<Branch> opOldBranch = branchRepository.findById(id);
        if (opOldBranch.isEmpty()) throw new ResourceNotFoundException("Branch","id",id);
        Branch oldBranch = opOldBranch.get();
        if(branch.getAddress()!=null) oldBranch.setAddress(branch.getAddress());
        if(branch.getVehicleDay()!=null) oldBranch.setVehicleDay(branch.getVehicleDay());
        if(branch.getWarehouse()!=null) oldBranch.setWarehouse(branch.getWarehouse());
        return branchRepository.save(oldBranch);
    }

    public void deleteBranch(Integer id){
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isEmpty()) throw new ResourceNotFoundException("Branch","id",id);
        branchRepository.delete(branch.get());
    }

    public Vehicle createVehicle(VehicleDTO vehicleDTO){
        if(vehicleDTO.getId()!=null){
            Optional<Vehicle> vehicleOptional= vehicleRepository.findById(vehicleDTO.getId());
            if(vehicleOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"ID Already Exists");
        }
        Vehicle vehicle;
        if(vehicleDTO.getBranchId()!=null){
            Optional<Branch> branchOptional=branchRepository.findById(vehicleDTO.getBranchId());
            if(branchOptional.isEmpty()) throw new ResourceNotFoundException("Branch","ID",vehicleDTO.getBranchId());
            vehicle= new Vehicle(vehicleDTO.getId(),vehicleDTO.getPlate(),vehicleDTO.getVehiculeType(),branchOptional.get(),vehicleDTO.getMaxWeight());
        }else {
            vehicle= new Vehicle(vehicleDTO.getId(),vehicleDTO.getPlate(),vehicleDTO.getVehiculeType(),null,vehicleDTO.getMaxWeight());
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
