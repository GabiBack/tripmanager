package com.example.tripmanager.controller;

import com.example.tripmanager.email.EmailBuildService;
import com.example.tripmanager.email.EmailService;
import com.example.tripmanager.entity.Trip;
import com.example.tripmanager.entity.User;
import com.example.tripmanager.service.ConfirmationTokenService;
import com.example.tripmanager.service.UserService;
import com.example.tripmanager.service.UserTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/name/{name}")
    public User confirmUserName(@PathVariable("name") String name) {
        return userService.findUserByName(name);
    }

    @GetMapping("/token/{userId}")
    public String createTokenAndSendEmail(@PathVariable("userId") Long id) {
        User user = userService.findUserById(id);
        String token = confirmationTokenService.createConfirmationToken(id);
        String link = "localhost:8080/confirm?token="
                        .concat(token)
                        .concat("&userId=")
                        .concat(user.getId().toString());
        emailService.send(user.getEmail(), emailBuildService.buildEmail(user.getName(), link));

        return "Email send";
    }

    @GetMapping("/confirm")
    public Boolean confirmUser(@RequestParam("token") String token,
                               @RequestParam("userId") Long userId){
        return confirmationTokenService.confirmToken(token,userId);
    }

    @GetMapping("/user/{id}")
    public User user(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/user/trip/{userId}")
    public List<Trip> userTrips(@PathVariable("userId") Long id){

        return userTripService.findUsersTripsById(id);
    }

}
