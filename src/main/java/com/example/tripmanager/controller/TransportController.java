package com.example.tripmanager.controller;

import com.example.tripmanager.entity.Transport;
import com.example.tripmanager.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class TransportController {

    @Autowired
    private TransportService transportService;

    @CrossOrigin(origins = "*")
    @PostMapping("/transport/{tripId}")
    public Transport addNewTransport(@PathVariable("tripId") Long id,
                                     @RequestBody Transport transport) {

        return transportService.saveTransport(transport, id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/transports/{tripId}")
    public List<Transport> showTransports(@PathVariable("tripId") Long tripId) {

        return transportService.showTransports(tripId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/transport/{id}")
    public Optional<Transport> showTransport(@PathVariable("id") Long id) {

        return transportService.showTransport(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/transport/{id}")
    public Transport updateTransport(@PathVariable("id") Long id,
                                     @RequestBody Transport transport) {
        return transportService.updateTransport(id, transport);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/transport/{id}")
    public void deleteTransport(@PathVariable("id") Long id){
            transportService.deleteTransport(id);
    }

}
