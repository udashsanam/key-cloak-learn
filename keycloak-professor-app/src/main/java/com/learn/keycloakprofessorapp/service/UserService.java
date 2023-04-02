package com.learn.keycloakprofessorapp.service;

import com.learn.keycloakprofessorapp.entity.User;
import com.learn.keycloakprofessorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
