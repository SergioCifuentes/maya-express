package com.mayaexpress.controller;

import com.mayaexpress.dto.request.BranchDTO;
import com.mayaexpress.dto.request.ShipmentDTO;
import com.mayaexpress.dto.request.VehicleDTO;
import com.mayaexpress.entity.*;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/department")
    public ResponseEntity<List<Department>> getDepartments(){
        try{
            return ResponseEntity.ok(shipmentService.getDepartments());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartment(@Valid @PathVariable Integer id){
            return ResponseEntity.ok(shipmentService.getDepartment(id));

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/price")
    public ResponseEntity<List<Price>> getPrices(){
        try{
            return ResponseEntity.ok(shipmentService.getPrices());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/price/{id}")
    public ResponseEntity<Price> getPrice(@Valid @PathVariable Integer id){
        return ResponseEntity.ok(shipmentService.getPrice(id));

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/price/{idOrigen}/{idDestination}")
    public ResponseEntity<Price> getPriceByDepartments(@Valid @PathVariable Integer idOrigen,@Valid @PathVariable Integer idDestination){
        return ResponseEntity.ok(shipmentService.getPriceByDepartments(idOrigen,idDestination));

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PutMapping("/price/{id}")
    public ResponseEntity<Price> updatePrice(@Valid @RequestBody Price price, @Valid @PathVariable Integer id){
        return ResponseEntity.ok(shipmentService.updatePrice(price,id));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/branch")
    public ResponseEntity<Branch> createBranch(@Valid @RequestBody BranchDTO branch){
        try {
            Branch newBranch =shipmentService.createBranch(branch);
            Integer id = newBranch.getId().intValue();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/branch/{id}")
    public ResponseEntity<Branch> getBranch( @Valid @PathVariable Integer id){
        return ResponseEntity.ok(shipmentService.getBranch(id));

    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/branch")
    public ResponseEntity<List<Branch>> getBranches(){
        try{
            return ResponseEntity.ok(shipmentService.getBranches());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/branch/{id}")
    public ResponseEntity<Branch> updateBranch(@Valid @RequestBody Branch branch, @Valid @PathVariable Integer id){
        return ResponseEntity.ok(shipmentService.updateBranch(branch,id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/branch/{id}")
    public ResponseEntity<?> deleteBranch( @Valid @PathVariable Integer id){
        shipmentService.deleteBranch(id);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        try {
            Vehicle newVehicle =shipmentService.createVehicle(vehicleDTO);
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
        return ResponseEntity.ok(shipmentService.getVehicle(id));

    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/vehicle")
    public ResponseEntity<List<Vehicle>> getVehicles(){
        try{
            return ResponseEntity.ok(shipmentService.getVehicles());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicle, @Valid @PathVariable Integer id){
        return ResponseEntity.ok(shipmentService.updateVehicle(vehicle,id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle( @Valid @PathVariable Integer id){
        shipmentService.deleteVehicle(id);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/send")
    public ResponseEntity<Shipment> sendShipment(@Valid @RequestBody ShipmentDTO shipmentDTO){
        try {
            Shipment newShipment =shipmentService.send(shipmentDTO);
            Integer id = newShipment.getId().intValue();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
