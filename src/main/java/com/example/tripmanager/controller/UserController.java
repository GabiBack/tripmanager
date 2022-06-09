package com.example.tripmanager.controller;

import com.example.tripmanager.email.EmailBuildService;
import com.example.tripmanager.email.EmailRequest;
import com.example.tripmanager.email.EmailSendgridService;
import com.example.tripmanager.email.EmailService;
import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.entity.User;
import com.example.tripmanager.service.ConfirmationTokenService;
import com.example.tripmanager.service.UserService;
import com.example.tripmanager.service.UserTripService;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping
@RestController
public class UserController {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailBuildService emailBuildService;
    @Autowired
    private UserTripService userTripService;
    @Autowired
    private EmailSendgridService emailSendgridService;

    @CrossOrigin(origins = "*")
    @GetMapping("/user/name/{name}")
    public User confirmUserName(@PathVariable("name") String name) {

        return userService.findUserByName(name);
    }

//    @CrossOrigin(origins = "*")
//    @GetMapping("/token/{userId}")
//    public ResponseEntity<String> createTokenAndSendEmail(@PathVariable("userId") Long id) throws IOException {
//       Response response = emailSendgridService.sendEmail(id);
//        if(response.getStatusCode()==200 || response.getStatusCode()==202) {
//            return new ResponseEntity<>("send successfully", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("failed to send", HttpStatus.NOT_FOUND);
//    }

    @CrossOrigin(origins = "*")
    @GetMapping("/token/{userId}")
    public String createTokenAndSendEmail(@PathVariable("userId") Long id) {
        User user = userService.findUserById(id);
        String token = confirmationTokenService.createConfirmationToken(id);
        String link = "localhost:8081/validate-token/"
                .concat(user.getId().toString())
                .concat("/")
                .concat(token);
        emailService.send(user.getEmail(), emailBuildService.buildEmail(user.getName(), link));

        return "Email send";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/confirm")
    public Boolean confirmUser(@RequestParam("token") String token,
                               @RequestParam("userId") Long userId){
        return confirmationTokenService.confirmToken(token,userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/{id}")
    public User user(@PathVariable("id") Long id) {

        return userService.findUserById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/user/trip/{userId}")
    public List<Trip> userTrips(@PathVariable("userId") Long id){

        return userTripService.findUsersTripsByUserId(id);
    }

}
