package com.mayaexpress.service;

import com.mayaexpress.dto.RouteDTO;
import com.mayaexpress.dto.request.TripCreationDTO;
import com.mayaexpress.entity.*;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.RouteRepository;
import com.mayaexpress.repository.ShipmentHistoryRepository;
import com.mayaexpress.repository.ShipmentTripRepository;
import com.mayaexpress.repository.TripRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    private final TripRepository tripRepository;

    private final ShipmentHistoryRepository shipmentHistoryRepository;

    private  final ShipmentTripRepository shipmentTripRepository;

    @Value("${route.max.wait-time}")
    private Integer waitTime;

    public RouteService(RouteRepository routeRepository, TripRepository tripRepository, ShipmentHistoryRepository shipmentHistoryRepository, ShipmentTripRepository shipmentTripRepository) {
        this.routeRepository = routeRepository;
        this.tripRepository = tripRepository;
        this.shipmentHistoryRepository = shipmentHistoryRepository;
        this.shipmentTripRepository = shipmentTripRepository;
    }


    public Integer createTrips(TripCreationDTO tripCreationDTO){
        List<Route> routes = routeRepository.findAll();
        List<Trip> trips= new ArrayList<>();
        Calendar start = tripCreationDTO.getStartDate();
        Calendar end = tripCreationDTO.getEndDate();


        while (start.before(end)){
            trips.addAll(createTripsByDate(start,routes));
            start.add(Calendar.DATE, 1);

        }

        trips.addAll(createTripsByDate(start,routes));
        return tripRepository.saveAll(trips).size();
    }

    public List<Trip> createTripsByDate(Calendar date, List<Route> routes){


        Integer day=date.get(Calendar.DAY_OF_WEEK)-1;
        List<Trip> trips= new ArrayList<>();
        Calendar c=Calendar.getInstance();
        for (Route route: routes){
            if (route.getDepartureDay().ordinal()==day){
                Calendar newCalendar= (Calendar) date.clone();
                c.setTime(route.getDepartureTime());
                int hour =c.get(Calendar.HOUR_OF_DAY);

                newCalendar.set(Calendar.HOUR_OF_DAY,hour);

                trips.add(new Trip(null, 0f, route, newCalendar.getTime()));
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

    public void findRoute(Shipment shipment, Double weight) {
        Calendar calendar= Calendar.getInstance();
        calendar.setTime((Date) shipment.getSendDate().clone());
        calendar.add(Calendar.HOUR_OF_DAY, waitTime);
        Date maxDate= calendar.getTime();
        List<Trip> trips=tripRepository.getTripsByDateAfterAndDateBefore(shipment.getSendDate(),maxDate);
        trips=removeTripsByWarehouse(shipment.getSendingWarehouse(),trips);
        List<Trip> tryTrips=getTripsToTry(shipment.getSendingWarehouse(),trips);

        HashMap<Warehouse,Integer> hoursForWarehouse=new HashMap<>();
        RouteDTO shipmentTrips=getRoute(shipment.getSendingWarehouse(),shipment.getReceiveWarehouse(),1,waitTime,
                tryTrips,trips,shipment.getSendDate(),hoursForWarehouse);
        if (shipmentTrips!=null){
            System.out.println(shipmentTrips);
            shipmentTrips.getTripList().forEach(System.out::println);
            saveRoute(shipmentTrips,shipment,shipment.getSendDate());
        }else{
            throw new APIException(HttpStatus.CONFLICT,"No se encontro ruta posible");
        }
    }

    private RouteDTO getRoute(Warehouse current, Warehouse destination,
                                      int currentTime, int bestTime, List<Trip> tryTrips, List<Trip> tripsRemain,
                                        Date sendDate, HashMap<Warehouse,Integer> hoursForWarehouse){
        RouteDTO best=null;
       if(destination.getId()==current.getId()){
            return new RouteDTO(new ArrayList<>(),currentTime,true);
        }
        for (Trip trip:tryTrips) {
            RouteDTO newRoute=new RouteDTO(trip,currentTime,false);

            Warehouse destinationWarehouse=(current.getId()==trip.getRoute().getHomeWarehouse().getId())?
                    trip.getRoute().getAwayWarehouse():trip.getRoute().getHomeWarehouse();
            List<Trip> tripsRemainCopy=new ArrayList<>(tripsRemain);
            tripsRemainCopy=removeTripsByWarehouse(destinationWarehouse,tripsRemainCopy);
            Integer timeTo = getTimeToTripDestination(trip,sendDate);
            if(bestTime<=currentTime||(hoursForWarehouse.containsKey(destinationWarehouse)&&timeTo>hoursForWarehouse.get(destinationWarehouse))){

                continue;}
            else{
                hoursForWarehouse.put(destinationWarehouse,timeTo);
            }

            tripsRemainCopy=removeTripsByTimePast(timeTo,tripsRemainCopy,sendDate);
            List<Trip> newTryTrips= getTripsToTry(destinationWarehouse,tripsRemainCopy);

            RouteDTO nextRoute =getRoute(destinationWarehouse,destination,timeTo,bestTime,newTryTrips,tripsRemainCopy,sendDate,hoursForWarehouse);
            if(nextRoute==null || !nextRoute.isComplete()) continue;
            newRoute.addRoute(nextRoute);
            if (best==null||(newRoute.isComplete()&&(newRoute!=null && (best.getTime()> newRoute.getTime())))){
                best=newRoute;
                if(nextRoute.isComplete()) bestTime=nextRoute.getTime();
            }
        }

        return best;
    }
    private void saveRoute(RouteDTO route, Shipment shipment,Date date){
        ShipmentHistory sh = new ShipmentHistory(null,shipment,HistoryState.ENTRANCE,date,shipment.getSendingWarehouse(),route.getTripList().get(0));
        shipmentHistoryRepository.save(sh);
        int i =1;
        for (Trip tr:route.getTripList()) {
            ShipmentTrip st = new ShipmentTrip(null, shipment,i,tr);
              i++;
              shipmentTripRepository.save(st);
        }

    }

    private Integer getTimeToTripDestination(Trip trip, Date sendDate) {
        Calendar c =Calendar.getInstance();
        c.setTime((Date) trip.getDate().clone());
        c.add(Calendar.HOUR,trip.getRoute().getHours());
        long sec=(c.getTime().getTime() - sendDate.getTime()) / 1000;
        return (int)sec/3600;

    }

    private List<Trip> getTripsToTry(Warehouse warehouse, List<Trip> trips){
        List<Trip> tryTrips;
        tryTrips=trips.stream()
                .filter(trip ->(trip.getRoute().getIsDeparture()&& trip.getRoute().getHomeWarehouse().getId()==warehouse.getId())||
                        (!trip.getRoute().getIsDeparture()&&trip.getRoute().getAwayWarehouse().getId()==warehouse.getId()))
                .collect(Collectors.toList());
        trips.removeAll(tryTrips);
        return tryTrips;
    }
    private List<Trip> removeTripsByWarehouse(Warehouse warehouse, List<Trip> trips){
        trips = trips.stream()
                .filter(trip ->(trip.getRoute().getIsDeparture()&& trip.getRoute().getAwayWarehouse().getId()!=warehouse.getId())||
                        (!trip.getRoute().getIsDeparture()&&trip.getRoute().getHomeWarehouse().getId()!=warehouse.getId()))
                .collect(Collectors.toList());

        return trips;
    }


    private List<Trip> removeTripsByTimePast(Integer timePast, List<Trip> trips, Date date){
        Calendar c = Calendar.getInstance();
        c.setTime((Date) date.clone());
        c.add(Calendar.HOUR,timePast);
        trips = trips.stream()
                .filter(trip -> trip.getDate().after(c.getTime())||
                        trip.getDate().compareTo(c.getTime())==0)
                .collect(Collectors.toList());
        return trips;
    }
}
