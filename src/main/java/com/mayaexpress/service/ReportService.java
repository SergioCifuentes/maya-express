package com.mayaexpress.service;

import com.mayaexpress.dto.request.DateDTO;
import com.mayaexpress.dto.response.MostPopularDestinationDTO;
import com.mayaexpress.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final DestinationRepository destinationRepository;

    public ReportService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
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
}
