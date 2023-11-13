package com.mayaexpress.service;

import com.mayaexpress.dto.response.MostPopularDestinationDTO;
import com.mayaexpress.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final DestinationRepository destinationRepository;

    public ReportService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<MostPopularDestinationDTO> getMostPopularDestinations(){
        List<Object[]> results = destinationRepository.findMostPopularDestinations();
        return results.stream()
                .map(MostPopularDestinationDTO::new)
                .collect(Collectors.toList());
    }
}
