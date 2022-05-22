package com.example.tripmanager.controller;

import com.example.tripmanager.entity.User;
import com.example.tripmanager.service.ConfirmationTokenService;
import com.example.tripmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:3000")
public class UserValidationController {

    @Autowired
    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;


//    @GetMapping("/user-validate/{email}")
//    public Optional<User> userValidate(@PathVariable("email") String email){
//        return userService.findByEmail(email);
//    }

    @GetMapping("/users")
    public List<User> users(){
        return userService.getUsers();
    }
}
