package com.example.tripmanager.controller;

import com.example.tripmanager.entity.Image;
import com.example.tripmanager.response.MessageResponse;
import com.example.tripmanager.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload/file/{tripId}")
    public ResponseEntity<MessageResponse> uploadFile(@PathVariable("tripId") Long tripId,
                                                      @RequestParam("file")MultipartFile multipartFile) {
        String message = "";
        try {
            imageService.saveImage(multipartFile, tripId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(MessageResponse.builder()
                            .message(message
                                    .concat("Uploaded file successfully ")
                                    .concat(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                            .build());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                            .body(MessageResponse.builder()
                                    .message(message
                                            .concat("Couldn't upload the file ")
                                            .concat(Objects.requireNonNull(multipartFile.getOriginalFilename())))
                                    .build());
        }
    }

    @GetMapping("file/{tripId}")
    public ResponseEntity<byte[]> getImage(@PathVariable("tripId") Long tripId) {
        Optional<Image> image = imageService.getImage(tripId);
        if(image.isPresent()){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "image; filename=\"" + image.get().getName() + "\"")
                    .body(image.get().getData());
        } else {
            throw new NullPointerException("TThere is no image for this trip");
        }
    }

    //edycja obrazka
    //usuwanie obrazka
}
