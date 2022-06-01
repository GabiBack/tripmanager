package com.example.tripmanager.controller;

import com.example.tripmanager.entity.Arrangement;
import com.example.tripmanager.service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ArrangementController {

    @Autowired
    private ArrangementService arrangementService;

    //dodaj notatke "co zabrac" ma miec jako {type} 1, a "co zrobić ma {type} 2
    @CrossOrigin(origins = "*")
    @PostMapping("/arrangement/{type}/{tripId}")
    public Arrangement addNewArrangement(@PathVariable("type") int type,
                                         @PathVariable("tripId") Long tripId,
                                         @RequestBody Arrangement arrangement) {

        return arrangementService.saveArrangement(type, arrangement, tripId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/arrangements/{type}/{tripId}")
    public List<Arrangement> arrangements(@PathVariable("type") int type,
                                          @PathVariable("tripId") Long tripId) {

        return arrangementService.showArrangements(type, tripId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/arrangement/{id}")
    public Optional<Arrangement> arrangement(@PathVariable("id") Long id) {

        return arrangementService.showArrangement(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/arrangement/{id}")
    public Arrangement updateArrangement(@PathVariable("id") Long id,
                                         @RequestBody Arrangement arrangement) {

        return arrangementService.updateArrangement(id, arrangement);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("arrangement/{id}")
    public void deleteArrangement(@PathVariable("id") Long id) {

        arrangementService.deleteArrangement(id);
    }
}
