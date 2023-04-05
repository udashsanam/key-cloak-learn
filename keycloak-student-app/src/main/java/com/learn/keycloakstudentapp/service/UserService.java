package com.learn.keycloakstudentapp.service;

import com.learn.keycloakstudentapp.entity.User;
import com.learn.keycloakstudentapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository  userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
