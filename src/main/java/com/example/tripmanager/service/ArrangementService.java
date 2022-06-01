package com.example.tripmanager.service;

import com.example.tripmanager.entity.Arrangement;
import com.example.tripmanager.repository.ArrangementRepository;
import com.example.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Transactional
    public Optional<Arrangement> showArrangement(Long id) {
        Optional<Arrangement> arrangement = arrangementRepository.findById(id);

        if(arrangement.isPresent()) return arrangement;
        else throw new IllegalStateException("There is no arrangement with this id");
    }

    @Transactional
    public Arrangement updateArrangement(Long id, Arrangement arrangement) {
        Optional<Arrangement> existingArrangement = arrangementRepository.findById(id);
        if(existingArrangement.isPresent()) {
            existingArrangement.get().setNote(arrangement.getNote());
            arrangementRepository.save(existingArrangement.get());
        } else throw new NullPointerException("There is no arrangement with this id");
        return existingArrangement.get();
    }

    @Transactional
    public void deleteArrangement(Long id) {
        Optional<Arrangement> arrangement = arrangementRepository.findById(id);
        arrangement.ifPresent(value -> arrangementRepository.delete(value));
    }
}
