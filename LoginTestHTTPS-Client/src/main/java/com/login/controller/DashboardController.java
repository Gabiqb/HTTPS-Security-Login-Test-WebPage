package com.login.controller;

import com.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DashboardController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = restTemplate.getForObject("https://localhost:8082/get/" + auth.getName(), User.class);
        modelAndView.addObject("currentUser", user);
        if (user != null) {
            modelAndView.addObject("fullName", "Welcome " + user.getName());
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("dashboard");
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

}
