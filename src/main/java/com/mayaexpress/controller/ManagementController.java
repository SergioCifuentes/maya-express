package com.mayaexpress.controller;

import com.mayaexpress.dto.request.VehicleDTO;
import com.mayaexpress.entity.Vehicle;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.ManagementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/management")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        try {
            Vehicle newVehicle = managementService.createVehicle(vehicleDTO);
            Integer id = newVehicle.getId().intValue();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicle( @Valid @PathVariable Integer id){
        return ResponseEntity.ok(managementService.getVehicle(id));

    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/vehicle")
    public ResponseEntity<List<Vehicle>> getVehicles(){
        try{
            return ResponseEntity.ok(managementService.getVehicles());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle, @Valid @PathVariable Integer id){
        return ResponseEntity.ok(managementService.updateVehicle(vehicle,id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle( @Valid @PathVariable Integer id){
        managementService.deleteVehicle(id);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }
}
