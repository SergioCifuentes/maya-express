package com.mayaexpress.controller;

import com.mayaexpress.entity.Price;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.WarehouseService;
import com.mayaexpress.util.CommonParams;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/api/admin/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Warehouse> create(@Valid @RequestBody Warehouse warehouse){
        try {
            warehouseService.create(warehouse);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(warehouse.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> update(@Valid @RequestBody Warehouse warehouse, @Valid @PathVariable Integer id){
        try {
            return ResponseEntity.ok(warehouseService.update(warehouse, id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> delete(@Valid @PathVariable Integer id){
        try {
            warehouseService.delete(id);
            return ResponseEntity.ok().build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all-assets/")
    public ResponseEntity<Page<Warehouse>> getAllAssets(CommonParams commonParams){
        try {
            return ResponseEntity.ok(warehouseService.getAllActivates(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

   @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all-deactivated/")
    public ResponseEntity<Page<Warehouse>> getAllDeactivated(CommonParams commonParams){
        try {
            return ResponseEntity.ok(warehouseService.getAllDeactivates(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all/")
    public ResponseEntity<Page<Warehouse>> getAll(CommonParams commonParams){
        try {
            return ResponseEntity.ok(warehouseService.getAll(commonParams.getPage(), commonParams.getMax(), commonParams.isPagination()));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@Valid @PathVariable Integer id){
        try {
            return ResponseEntity.ok(warehouseService.get(id));
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/branch")
    public ResponseEntity<List<Warehouse>> getBranches(){
        try{
            return ResponseEntity.ok(warehouseService.getBranches());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/branch/{id}")
    public ResponseEntity<Warehouse> getBranch(@Valid @PathVariable Integer id){
        return ResponseEntity.ok(warehouseService.getBranch(id));

    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/{id}/trips")
    public ResponseEntity<ResponseEntity> getTrips(@Valid @PathVariable Integer id){
        try{
            return ResponseEntity.ok(warehouseService.getTrips(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('IT')")
    @GetMapping("/branch-by-department/{id}")
    public ResponseEntity<List<Warehouse>> getBranchesByDepartment(@Valid @PathVariable Integer id){
        try{
            return ResponseEntity.ok(warehouseService.getBranchesById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
