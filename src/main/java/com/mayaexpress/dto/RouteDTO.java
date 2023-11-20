package com.mayaexpress.dto;

import com.mayaexpress.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RouteDTO {

    private List<Trip> tripList;

    private Integer time;

    private boolean complete=false;

    public RouteDTO(Trip trip, Integer time, boolean complete) {
        this.tripList = new ArrayList<>();
        tripList.add(trip);
        this.time = time;
        this.complete = complete;
    }

    public void addRoute(RouteDTO nextRoute) {
        this.tripList.addAll(nextRoute.getTripList());
        this.time=nextRoute.getTime();
        this.complete=nextRoute.isComplete();
    }

    @Override
    public String toString() {
        return "RouteDTO{" +
                "tripList=" + tripList +
                ", time=" + time +
                ", complete=" + complete +
                '}';
    }
}
