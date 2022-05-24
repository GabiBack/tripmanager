package com.example.tripmanager.service;

import com.example.tripmanager.entity.Transport;
import com.example.tripmanager.repository.TransportRepository;
import com.example.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TransportService {

 @Autowired
 private TransportRepository transportRepository;

 @Autowired
 private TripRepository tripRepository;


  @Transactional
  public Transport saveTransport(Transport transport, Long tripId) {

   return transportRepository.save(Transport.builder()
                   .name(transport.getName())
                   .startDate(transport.getStartDate())
                   .startPlace(transport.getStartPlace())
                   .endDate(transport.getEndDate())
                   .endPlace(transport.getEndPlace())
                   .order(transport.getOrder())
                   .notes(transport.getNotes())
                   .trip(tripRepository.findById(tripId).get())
                   .build());
  }

  @Transactional
  public List<Transport> showTransports(Long id) {
   List<Transport> transports = transportRepository.findAll();
   List<Transport> thisTripTransports = new ArrayList<>();

     for(Transport t : transports){
        Long tripId = t.getTrip().getId();
        if(tripId.equals(id)) {
         thisTripTransports.add(t);
        }
     }
     thisTripTransports.sort(Comparator.comparingInt(Transport::getOrder));

     return thisTripTransports;
  }
}
