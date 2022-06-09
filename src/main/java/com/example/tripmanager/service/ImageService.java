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

    @Transactional
    public Optional<Image> editImage(Long imageId, MultipartFile multipartFile) throws IOException {
        Optional<Image> currentImage = imageRepository.findById(imageId);
        if(currentImage.isPresent()){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            currentImage.get().setName(fileName);
            currentImage.get().setType(multipartFile.getContentType());
            currentImage.get().setData(multipartFile.getBytes());
            imageRepository.save(currentImage.get());
        }
        return Optional.of(currentImage.get());
    }


    private Optional<Image> findImageByTripId(Long tripId) {
        List<Image> images = imageRepository.findAll();
        System.out.println(images);
        Image image = null;
        for(Image currentImage : images) {
            Long currentImageTripId = currentImage.getTrip().getId();
            if(currentImageTripId.equals(tripId)) image = currentImage;
        }

        return Optional.ofNullable(image);
    }
}
