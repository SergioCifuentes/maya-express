package com.mayaexpress.controller;

import com.mayaexpress.dto.request.ShipmentDTO;
import com.mayaexpress.entity.Cost;
import com.mayaexpress.entity.CostType;
import com.mayaexpress.entity.Shipment;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.CostService;
import com.mayaexpress.util.CommonParams;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController()
@RequestMapping("/api/admin/cost")
public class CostController {

    private final CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @PostMapping("/")
    public ResponseEntity<Shipment> addCost(@Valid @RequestBody Cost cost){
        try {
            Cost costRequest = costService.addCost(cost);
            Integer id = costRequest.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allCostType/")
    public ResponseEntity<Page<CostType>> getAllCostType(CommonParams commonParams){
        try {
            return ResponseEntity.ok(costService.getAllCostType(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
