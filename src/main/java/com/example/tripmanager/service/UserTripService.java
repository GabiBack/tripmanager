package com.example.tripmanager.service;

import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.entity.UserTrip;
import com.example.tripmanager.repository.UserTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTripService {

    @Autowired
    private UserTripRepository userTripRepository;
    @Autowired
    private TripService tripService;

    public List<Trip> findUsersTripsById(Long id){
        List<UserTrip> userTripList = userTripRepository.findAll();
        List<Trip> theUserTrips = null;
        for(UserTrip userTrip : userTripList) {
            if(userTrip.getUserId().equals(id)) {
                theUserTrips.add(tripService.getTrip(userTrip.getTripId()).get());
            }
        }
        return theUserTrips;
    }
}
