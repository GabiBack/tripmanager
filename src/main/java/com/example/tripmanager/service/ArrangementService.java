package com.example.tripmanager.service;

import com.example.tripmanager.entity.Arrangement;
import com.example.tripmanager.repository.ArrangementRepository;
import com.example.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ArrangementService {

    @Autowired
    ArrangementRepository arrangementRepository;

    @Autowired
    TripRepository tripRepository;

    @Transactional
    public Arrangement saveArrangement(int type, Arrangement arrangement, Long tripId) {
        return arrangementRepository.save(Arrangement.builder()
                        .note(arrangement.getNote())
                        .type(type)
                        .trip(tripRepository.findById(tripId).get())
                        .build());

    }

    @Transactional
    public List<Arrangement> showArrangements(int type, Long tripId) {
        List<Arrangement> allArrangements = arrangementRepository.findAll();
        List<Arrangement> arrangementsByGivenType = new ArrayList<>();
        for(Arrangement a:allArrangements){
            if(a.getType()==type && a.getTrip().getId().equals(tripId)) {
                arrangementsByGivenType.add(a);
            }
        }
        return arrangementsByGivenType;
    }
}
