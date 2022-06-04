package com.example.tripmanager.service;

import com.example.tripmanager.entity.Image;
import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.repository.ImageRepository;
import com.example.tripmanager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private TripRepository tripRepository;

    @Transactional
    public Image saveImage(MultipartFile multipartFile, Long tripId) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Image attachment = Image.builder()
                .name(fileName)
                .type(multipartFile.getContentType())
                .data(multipartFile.getBytes())
                .trip(tripRepository.findById(tripId).get())
                .build();

        return imageRepository.save(attachment);
    }

    @Transactional
    public Optional<Image> getImage(Long tripId) {
        return findImageByTripId(tripId);
    }


    private Optional<Image> findImageByTripId(Long tripId) {
        List<Image> images = imageRepository.findAll();
        Image image = null;

        for(Image currentImage : images){
            Trip currentTrip = currentImage.getTrip();
            Long currentTripId = currentTrip.getId();
            if(currentTripId.equals(tripId)) image = currentImage;
        }
        return Optional.ofNullable(image);

    }
}
