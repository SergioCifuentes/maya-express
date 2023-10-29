package com.mayaexpress.controller;

import com.mayaexpress.dto.request.EmployeeAssignmentDTO;
import com.mayaexpress.dto.request.VehicleAssignmentDTO;
import com.mayaexpress.entity.Employee;
import com.mayaexpress.entity.Vehicle;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.EmployeeService;
import com.mayaexpress.service.VehicleService;
import com.mayaexpress.util.CommonParams;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    private final EmployeeService employeeService;
    private final VehicleService vehicleService;

    public AdminController(EmployeeService employeeService, VehicleService vehicleService) {
        this.employeeService = employeeService;
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee){
        try {
            employeeService.create(employee);
            Integer id = employee.getId().intValue();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> update(@Valid @RequestBody Employee employee, @Valid @PathVariable BigDecimal id){
        try {
            return ResponseEntity.ok(employeeService.update(employee, id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> delete(@Valid @PathVariable BigDecimal id){
        try {
            employeeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employee/all-assets/")
    public ResponseEntity<Page<Employee>> getAllAssets(CommonParams commonParams){
        try {
            return ResponseEntity.ok(employeeService.getAllActivates(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employee/all-deactivated/")
    public ResponseEntity<Page<Employee>> getAllDeactivated(CommonParams commonParams){
        try {
            return ResponseEntity.ok(employeeService.getAllDeactivates(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employee/all/")
    public ResponseEntity<Page<Employee>> getAll(CommonParams commonParams){
        try {
            return ResponseEntity.ok(employeeService.getAll(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/employee/{id}/")
    public ResponseEntity<Employee> get(@Valid @PathVariable BigDecimal id){
        try {
            return ResponseEntity.ok(employeeService.get(id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employee/assign-vehicle/")
    public ResponseEntity<Vehicle> assignVehicle(@Valid @RequestBody VehicleAssignmentDTO vehicleAssignmentDTO){
        try {
            return ResponseEntity.ok(vehicleService.assignVehicle(vehicleAssignmentDTO.getVehicle(), vehicleAssignmentDTO.getBranch()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employee/assign-employee/")
    public ResponseEntity<Employee> assignVehicle(@Valid @RequestBody EmployeeAssignmentDTO employeeAssignmentDTO){
        try {
            return ResponseEntity.ok(employeeService.assignEmployee(employeeAssignmentDTO.getEmployee(), employeeAssignmentDTO.getBranch()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
