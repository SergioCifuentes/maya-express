package com.mayaexpress.service;

import com.mayaexpress.dto.request.DateDTO;
import com.mayaexpress.dto.response.MostPopularDestinationDTO;
import com.mayaexpress.dto.response.MovementsByRegionDTO;
import com.mayaexpress.repository.DestinationRepository;
import com.mayaexpress.repository.ShipmentHistoryRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final DestinationRepository destinationRepository;
    private final ShipmentHistoryRepository shipmentHistoryRepository;

    public ReportService(DestinationRepository destinationRepository, ShipmentHistoryRepository shipmentHistoryRepository) {
        this.destinationRepository = destinationRepository;
        this.shipmentHistoryRepository = shipmentHistoryRepository;
    }

    public List<MostPopularDestinationDTO> getMostPopularDestinations(DateDTO dateDTO) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateFormat.parse(dateDTO.getStartDate());
        Date endDate = dateFormat.parse(dateDTO.getEndDate());

        List<Object[]> results = destinationRepository.findMostPopularDestinations(dateFormat.format(startDate), dateFormat.format(endDate));
        return results.stream()
                .map(MostPopularDestinationDTO::new)
                .collect(Collectors.toList());
    }

    public List<MovementsByRegionDTO> getMovementsByRegion(DateDTO dateDTO) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateFormat.parse(dateDTO.getStartDate());
        Date endDate = dateFormat.parse(dateDTO.getEndDate());

        List<Object[]> results = shipmentHistoryRepository.getMovementsByRegion(dateFormat.format(startDate), dateFormat.format(endDate));
        return results.stream()
                .map(MovementsByRegionDTO::new)
                .collect(Collectors.toList());
    }
}
