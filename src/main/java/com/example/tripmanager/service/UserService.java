package com.example.tripmanager.service;

import com.example.tripmanager.entity.User;
import com.example.tripmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User findUserByName(String name) {
        List<User> users = userRepository.findAll();
        User user = null;
        for (User u : users) {
            String userName = u.getName();
            if (userName.equals(name)) user = u;
        }
        if(user==null) throw new NullPointerException("user not found");

        return user;
    }


//    public Optional<User> findByEmail(String email) {
//        List<User> users = userRepository.findAll();
//        User user = null;
//        for (User u : users) {
//            String userEmail = u.getEmail();
//            if (userEmail.equals(email)) user = u;
//            else throw new NullPointerException("user does not exist");
//        }
//        return Optional.ofNullable(user);
//    }
}

//    @Transactional
//    public void saveTestEmployee(){
//        userRepository.save(User.builder()
//                .firstName("Test")
//                .lastName("Testowy")
//                .build());
//    }

