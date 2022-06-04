package com.example.tripmanager.controller;

import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.entity.User;
import com.example.tripmanager.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class TripController {

    @Autowired
    private TripService tripService;

    @CrossOrigin(origins = "*")
    @GetMapping("/trip/{id}")
    public Optional<Trip> getTrip(@PathVariable("id") Long id){

        return tripService.getTrip(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trips")
    public List<Trip> getTrips(){

        return tripService.getTrips();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trip/users/{tripId}")
    public List<User> tripParticipants(@PathVariable("tripId") Long tripId) {

        return tripService.tripMembers(tripId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/trip")
    public Trip addTrip(@RequestBody Trip trip){
        Trip createdTrip =  tripService.saveTrip(trip);

        return tripService.setTripDuration(createdTrip);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/trip/{id}")
    public void deleteTrip(@PathVariable("id") Long id) {
        tripService.deleteTrip(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/trip/{id}")
    public Trip updateTrip(@PathVariable("id") Long id, @RequestBody Trip trip){
        return tripService.updateTrip(id,trip);
    }

}
