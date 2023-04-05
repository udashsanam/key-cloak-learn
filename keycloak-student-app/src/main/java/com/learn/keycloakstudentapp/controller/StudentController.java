package com.learn.keycloakstudentapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {


//    @GetMapping(value = {"/","/login"})
//    public ModelAndView  login(){
//        ModelAndView modelAndView = new ModelAndView("login");
//
//        return modelAndView;
//    }

    @GetMapping(value = "/home")
    public ModelAndView  home(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping(value = "/manage-student")
    @PreAuthorize("hasAnyAuthority('PROFESSOR')")
    public ModelAndView  manageStudent(){
        ModelAndView modelAndView = new ModelAndView("managestudent");
        return modelAndView;
    }

    @GetMapping(value = "/access-denial")
    public ModelAndView  accessDenial(){
        ModelAndView modelAndView = new ModelAndView("access-denial");
        return modelAndView;
    }

    @GetMapping(value = "/contact-user")
    public ModelAndView contactUs(){
        ModelAndView modelAndView = new ModelAndView("contact-us");

        return modelAndView;
    }
}
