package com.example.tripmanager.service;

import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.entity.UserTrip;
import com.example.tripmanager.repository.UserTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTripService {

    @Autowired
    private UserTripRepository userTripRepository;
    @Autowired
    private TripService tripService;

    public List<Trip> findUsersTripsByUserId(Long userId){
        List<UserTrip> userTripList = userTripRepository.findAll();
        List<Trip> theUserTrips = new ArrayList<>();

        for(UserTrip userTrip : userTripList){
            Long currentUserId = userTrip.getUserId();
            if(currentUserId.equals(userId)) {
                Long tripId = userTrip.getTripId();
                Optional<Trip> currentTrip = tripService.getTrip(tripId);
                currentTrip.ifPresent(theUserTrips::add);
            }
        }

        return theUserTrips;
    }
}
