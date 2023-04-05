package com.learn.keycloakstudentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KeycloakStudentAppApplication {




	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = passwordEncoder();

/*		passwordEncoder.encode("sanam");
		passwordEncoder.encode("sanam");*/
		SpringApplication.run(KeycloakStudentAppApplication.class, args);
	}

	public static BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
