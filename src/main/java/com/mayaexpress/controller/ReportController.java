package com.mayaexpress.controller;

import com.mayaexpress.dto.request.DateDTO;
import com.mayaexpress.dto.response.MostPopularDestinationDTO;
import com.mayaexpress.dto.response.MovementsByRegionDTO;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.InternalServerException;
import com.mayaexpress.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController()
@RequestMapping("/api/admin/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/most-popular-destinations")
    public ResponseEntity<List<MostPopularDestinationDTO>> getMostPopularDestinations(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(reportService.getMostPopularDestinations(dateDTO));
        } catch (InternalServerException | ParseException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/movements-by-region")
    public ResponseEntity<List<MovementsByRegionDTO>> getMovementsByRegion(@Valid @RequestBody DateDTO dateDTO) {
        try {
            return ResponseEntity.ok(reportService.getMovementsByRegion(dateDTO));
        } catch (InternalServerException | ParseException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
