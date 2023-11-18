package com.mayaexpress.controller;

import com.mayaexpress.dto.request.DateDTO;
import com.mayaexpress.dto.response.PackagesByDepartmentDTO;
import com.mayaexpress.dto.response.PackagesByRegionDTO;
import com.mayaexpress.dto.response.WarehouseDTO;
import com.mayaexpress.service.DecisionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/decisional")
public class DecisionalController {

    private final DecisionalService decisionalService;

    public DecisionalController(DecisionalService decisionalService) {
        this.decisionalService = decisionalService;
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/suggestion-regions-send")
    public ResponseEntity<List<PackagesByRegionDTO>> getSuggestionRegionsSend(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(decisionalService.getSuggestionRegions(true, dateDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/suggestion-regions-receive")
    public ResponseEntity<List<PackagesByRegionDTO>> getSuggestionRegionsReceive(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(decisionalService.getSuggestionRegions(false, dateDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/suggestion-department-send")
    public ResponseEntity<List<PackagesByDepartmentDTO>> getSuggestionDepartmentSend(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(decisionalService.getSuggestionDepartment(true, dateDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/suggestion-department-receive")
    public ResponseEntity<List<PackagesByDepartmentDTO>> getSuggestionDepartmentReceive(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(decisionalService.getSuggestionDepartment(false, dateDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/branches-suggestions-employees")
    public ResponseEntity<List<WarehouseDTO>> getBranchesSuggestionsEmployees(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(decisionalService.getSuggestionWarehouse(dateDTO, true));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/branches-suggestions-vehicles")
    public ResponseEntity<List<WarehouseDTO>> getBranchesSuggestionsVehicles(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(decisionalService.getSuggestionWarehouse(dateDTO, false));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
