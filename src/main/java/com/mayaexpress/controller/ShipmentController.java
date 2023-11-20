package com.mayaexpress.controller;


import com.google.zxing.WriterException;
import com.mayaexpress.dto.request.*;

import com.mayaexpress.dto.response.GuideHistoryDTO;
import com.mayaexpress.dto.response.MovementsByRegionDTO;
import com.mayaexpress.dto.response.ShipmentHistoryDTO;
import com.mayaexpress.dto.response.ShipmentTripDTO;
import com.mayaexpress.entity.*;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.ManagementService;
import com.mayaexpress.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final ManagementService managementService;

    public ShipmentController(ShipmentService shipmentService, ManagementService managementService) {
        this.shipmentService = shipmentService;
        this.managementService = managementService;
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

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/price/{idOrigen}/{idDestination}")
    public ResponseEntity<Price> getPriceByDepartments(@Valid @PathVariable Integer idOrigen,@Valid @PathVariable Integer idDestination){
        return ResponseEntity.ok(shipmentService.getPriceByDepartments(idOrigen,idDestination));

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PutMapping("/price/{id}")
    public ResponseEntity<Price> updatePrice(@Valid @RequestBody Price price, @Valid @PathVariable Integer id){
        return ResponseEntity.ok(shipmentService.updatePrice(price,id));

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
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PutMapping("/pay-shipment/{id}")
    public ResponseEntity<ShipmentPayment> payShipment(@Valid @PathVariable Integer id){
        try {
            ShipmentPayment newPayment =shipmentService.payShipment(id);
            return ResponseEntity.ok(newPayment);
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/entrance")
    public ResponseEntity<List<ShipmentHistory>> registerEntrance(@Valid @RequestBody WarehouseMovementDTO warehouseMovementDTO) {
        try {
            List<ShipmentHistory> newHistory = shipmentService.registerEntrance(warehouseMovementDTO);
            return ResponseEntity.ok(newHistory);
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/send/{id}")
    public ResponseEntity<String> getQR(@Valid @PathVariable Integer id){
        try {
            return ResponseEntity.ok(shipmentService.getQR(id));
        } catch (InternalServerException | IOException | WriterException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/departure")
    public ResponseEntity<List<ShipmentHistory>> registerDeparture(@Valid @RequestBody WarehouseMovementDTO warehouseMovementDTO) {
        try {
            List<ShipmentHistory> newHistory = shipmentService.registerDeparture(warehouseMovementDTO);
            return ResponseEntity.ok(newHistory);
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT') or hasAnyAuthority('EMPLOYEE')")
    @PutMapping("/receive/")
    public ResponseEntity<ShipmentHistory> receive(@Valid @RequestBody ReceiveDTO receiveDTO){
        try {
            ShipmentHistory newHistory =shipmentService.receive(receiveDTO);
            return ResponseEntity.ok(newHistory);
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/locate-package/{id}")
    public ResponseEntity<ShipmentHistoryDTO> locatePackage(@Valid @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(shipmentService.locatePackage(id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/guides-by-department/{id}")
    public ResponseEntity<List<GuideHistoryDTO>> getGuidesByDepartment(@Valid @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(shipmentService.getGuideHistory(id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/createTrips")
    public ResponseEntity<String> createTrips(@Valid @RequestBody TripCreationDTO tripCreationDTO) {
        try {
            return ResponseEntity.ok("Trips created "+shipmentService.createTrips(tripCreationDTO));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/shipments-by-trip/{id}")
    public ResponseEntity<ShipmentTripDTO> getShipmentsByTrip(@Valid @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(managementService.getShipmentsByTrip(id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
