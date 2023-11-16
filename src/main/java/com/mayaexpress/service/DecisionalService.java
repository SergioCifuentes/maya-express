package com.mayaexpress.service;

import com.mayaexpress.dto.request.DateDTO;
import com.mayaexpress.dto.response.GuideHistoryDTO;
import com.mayaexpress.dto.response.PackagesByDepartmentDTO;
import com.mayaexpress.dto.response.PackagesByRegionDTO;
import com.mayaexpress.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecisionalService {

    private final ShipmentRepository shipmentRepository;

    @Value("${descisional.minimum-packages-by-region}")
    private Integer minimumPackagesByRegion;

    @Value("${descisional.minimum-packages-by-destination}")
    private Integer minimumPackagesByDestination;

    public DecisionalService (ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<PackagesByRegionDTO> getSuggestionRegions(Boolean isOrigin, DateDTO dateDTO) {
        List<Object[]> regionDTOS =  shipmentRepository.getPackageCounts(isOrigin, dateDTO.getStartDate(), dateDTO.getEndDate());

        return regionDTOS.stream()
                .map(PackagesByRegionDTO::new)
                .filter(dto -> dto.getPackages() >= minimumPackagesByRegion)
                .collect(Collectors.toList());
    }

    public List<PackagesByDepartmentDTO> getSuggestionDepartment(Boolean isOrigin, DateDTO dateDTO) {
        List<Object[]> regionDTOS =  shipmentRepository.get_package_counts_by_destination(isOrigin, dateDTO.getStartDate(), dateDTO.getEndDate());

        return regionDTOS.stream()
                .map(PackagesByDepartmentDTO::new)
                .filter(dto -> dto.getPackages() >= minimumPackagesByDestination)
                .collect(Collectors.toList());
    }
}
