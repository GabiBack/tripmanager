package com.example.tripmanager.service;

import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Transactional
    public Optional<Trip> getTrip(Long id){
        return tripRepository.findById(id);
    }

    @Transactional
    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

//    public Optional<User> findByEmail(String email) {
//        List<User> users = userRepository.findAll();
//        User user = null;
//        for (User u : users) {
//            String userEmail = u.getEmail();
//            if (userEmail.equals(email)) user = u;
//            else throw new NullPointerException("user does not exist");
//        }
//        return Optional.ofNullable(user);
//    }
    @Transactional
    public Trip saveTrip(Trip trip){
        return tripRepository.save(Trip.builder()
                    .place(trip.getPlace())
                    .dateStart(trip.getDateStart())
                    .dateEnd(trip.getDateEnd())
                    .build());
    }

    @Transactional
    public Trip setTripDuration(Trip trip){
        Optional<Trip> existingTrip = tripRepository.findById(trip.getId());
        if(existingTrip.isPresent()){
            existingTrip.get().setDays(this.calculateDays(trip));
            tripRepository.save(existingTrip.get());
        }

        return existingTrip.get();
    }

    @Transactional
    public void deleteTrip(Long id){
        Optional<Trip> trip =  tripRepository.findById(id);
        trip.ifPresent(value -> tripRepository.delete(value));

    }

    @Transactional
    public Trip updateTrip(Long id, Trip trip){
        Optional<Trip> existingTrip = tripRepository.findById(id);
        if(existingTrip.isPresent()){
            existingTrip.get().setPlace(trip.getPlace());
            existingTrip.get().setDateStart(trip.getDateStart());
            existingTrip.get().setDateEnd(trip.getDateEnd());
            existingTrip.get().setDays(this.calculateDays(trip));
            tripRepository.save(existingTrip.get());
        }

        return existingTrip.get();
    }

    private int calculateDays(Trip trip){
        LocalDate start = LocalDate.parse(trip.getDateStart());
        LocalDate end = LocalDate.parse(trip.getDateEnd());
        Period period = Period.between(start, end);
        return period.getDays();
    }
}


