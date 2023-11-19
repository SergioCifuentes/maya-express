package com.mayaexpress.service;

import com.mayaexpress.dto.request.TripCreationDTO;
import com.mayaexpress.entity.Route;
import com.mayaexpress.entity.Trip;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.RouteRepository;
import com.mayaexpress.repository.TripRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    private final TripRepository tripRepository;

    public RouteService(RouteRepository routeRepository, TripRepository tripRepository) {
        this.routeRepository = routeRepository;
        this.tripRepository = tripRepository;
    }

    public Integer createTrips(TripCreationDTO tripCreationDTO){
        List<Route> routes = routeRepository.findAll();
        List<Trip> trips= new ArrayList<>();
        Calendar start = tripCreationDTO.getStartDate();
        Calendar end = tripCreationDTO.getEndDate();
        start.add(Calendar.DATE, 1);
        end.add(Calendar.DATE, 1);

        while (start.before(end)){
            trips.addAll(createTripsByDate(start,routes));
            start.add(Calendar.DATE, 1);

        }
        trips.addAll(createTripsByDate(start,routes));
        return tripRepository.saveAll(trips).size();
    }

    public List<Trip> createTripsByDate(Calendar date, List<Route> routes){
        Integer day=(date.get(Calendar.DAY_OF_WEEK)==1)?6:date.get(Calendar.DAY_OF_WEEK)-2;
        List<Trip> trips= new ArrayList<>();
        for (Route route: routes){
            if (route.getDepartureDay().ordinal()==day){
                trips.add(new Trip(null, 0f, route, date.getTime()));
            }

        }

        return trips;
    }

    public Trip getTrip(Integer id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (optionalTrip.isEmpty()) {
            throw new ResourceNotFoundException("Trip", "ID", id);
        }
        return optionalTrip.get();
    }
}
