package com.example.tripmanager.controller;

import com.example.tripmanager.entity.Arrangement;
import com.example.tripmanager.service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ArrangementController {

    @Autowired
    private ArrangementService arrangementService;

    //dodaj notatke "co zabrac" ma miec jako {type} 1, a "co zrobić ma {type} 2
    @PostMapping("/arrangement/{type}/{tripId}")
    public Arrangement addNewArrangement(@PathVariable("type") int type,
                                         @PathVariable("tripId") Long tripId,
                                         @RequestBody Arrangement arrangement) {

        return arrangementService.saveArrangement(type, arrangement, tripId);
    }

    @GetMapping("/arrangement/{type}/{tripId}")
    public List<Arrangement> arrangements(@PathVariable("type") int type,
                                          @PathVariable("tripId") Long tripId) {

        return arrangementService.showArrangements(type, tripId);
    }
}
