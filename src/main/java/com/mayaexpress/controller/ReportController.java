package com.mayaexpress.controller;

import com.mayaexpress.dto.response.MostPopularDestinationDTO;
import com.mayaexpress.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/admin/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/most-popular-destinations")
    public ResponseEntity<List<MostPopularDestinationDTO>> getMostPopularDestinations() {
        return ResponseEntity.ok(reportService.getMostPopularDestinations());
    }
}
