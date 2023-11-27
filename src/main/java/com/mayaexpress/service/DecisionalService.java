package com.mayaexpress.service;

import com.mayaexpress.dto.request.DateDTO;
import com.mayaexpress.dto.response.*;
import com.mayaexpress.entity.Trip;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecisionalService {

    private final ShipmentRepository shipmentRepository;
    private final PackageRepository packageRepository;
    private final EmployeeRepository employeeRepository;
    private final VehicleRepository vehicleRepository;
    private final WarehouseService warehouseService;

    private final TripRepository tripRepository;

    @Value("${descisional.minimum-packages-by-region}")
    private Integer minimumPackagesByRegion;

    @Value("${descisional.minimum-packages-by-destination}")
    private Integer minimumPackagesByDestination;

    @Value("${descisional.percentage-balance}")
    private BigDecimal percentageBalance;

    @Value("${descisional.minimum-packages-by-employee}")
    private Integer minimumPackagesByEmployee;

    @Value("${descisional.minimum-packages-by-vehicle}")
    private Integer minimumPackagesByVehicle;

    @Value("${descisional.minimum-employees}")
    private Integer minimumEmployees;

    @Value("${descisional.minimum-vehicles}")
    private Integer minimumVehicles;

    @Value("${descisional.percentage-weight}")
    private Float minimumWeight;

    public DecisionalService (ShipmentRepository shipmentRepository, PackageRepository packageRepository, VehicleRepository vehicleRepository,
                              EmployeeRepository employeeRepository, WarehouseService warehouseService, TripRepository tripRepository) {
        this.shipmentRepository = shipmentRepository;
        this.packageRepository = packageRepository;
        this.employeeRepository = employeeRepository;
        this.vehicleRepository = vehicleRepository;
        this.warehouseService = warehouseService;
        this.tripRepository=tripRepository;
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

    public List<IncomeWarehouseDTO> getIncomeByWarehouse(DateDTO dateDTO) {
        List<Object[]> results = shipmentRepository.getExpensedByWarehouse(dateDTO.getStartDate(), dateDTO.getEndDate());
        return results.stream()
                .map(IncomeWarehouseDTO::new)
                .toList();
    }

    public List<WarehouseDTO> getSuggestionWarehouse(DateDTO dateDTO, Boolean isEmployee) {
        List<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        getIncomeByWarehouse(dateDTO).forEach(incomeWarehouseDTO -> {
            BigDecimal income = shipmentRepository.getIncomeAmount(dateDTO.getStartDate(), dateDTO.getEndDate(), incomeWarehouseDTO.getWarehouseId());
            income = income == null ? BigDecimal.valueOf(0) : income;
            BigDecimal balance = income.subtract(incomeWarehouseDTO.getIncome());
            if (!income.equals(BigDecimal.ZERO) && (balance.divide(income, BigDecimal.ROUND_DOWN)).compareTo(percentageBalance) >= 0) {
                Long packages = packageRepository.countPackagesByWarehouseAndDateRange(incomeWarehouseDTO.getWarehouseId(), dateDTO.getStartDate(), dateDTO.getEndDate());
                Integer minimum;
                int resources;
                Integer minimumResources;
                if (isEmployee) {
                    minimum = minimumPackagesByEmployee;
                    minimumResources = minimumEmployees;
                    resources = employeeRepository.findAllByWarehouse_Id(incomeWarehouseDTO.getWarehouseId()).size();
                } else {
                    minimum = minimumPackagesByVehicle;
                    minimumResources = minimumVehicles;
                    resources = vehicleRepository.findAllByWarehouse_Id(incomeWarehouseDTO.getWarehouseId()).size();
                }

                if (packages >= minimum && resources <= minimumResources) {
                    Warehouse warehouse = warehouseService.get(incomeWarehouseDTO.getWarehouseId());
                    warehouseDTOS.add(new WarehouseDTO(
                            warehouse,
                            balance,
                            isEmployee ? minimumResources - resources : 0,
                            isEmployee ? 0 : minimumResources - resources
                    ));
                }
            }
        });
        return warehouseDTOS;
    }
    public List<LightTripsDTO> getLightTrips() {
        List<Trip> tripsNotZero = tripRepository.getTripsNotZero();
        List<LightTripsDTO> lightTripsDTOS=new ArrayList<>();
        tripsNotZero.forEach(trip -> {
            if(trip.getRoute().getVehicle().getMaxWeight()*minimumWeight>trip.getCurrentWeight()){
                lightTripsDTOS.add(new LightTripsDTO(trip.getId(), trip.getCurrentWeight(),trip.getRoute().getVehicle().getId(),
                        trip.getRoute().getVehicle().getMaxWeight()));
            }
        });
        return lightTripsDTOS;
    }

}
