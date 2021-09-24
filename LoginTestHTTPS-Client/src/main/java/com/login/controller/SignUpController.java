package com.login.controller;

import com.login.model.User;
import com.login.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignUpController {
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public Object signup() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            ModelAndView modelAndView=new ModelAndView();
            User user= ((UserPrincipal) auth.getPrincipal()).getUser();
            modelAndView.addObject("currentUser", user);
            modelAndView.addObject("fullName", "Welcome" + user.getName());
            modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
            modelAndView.setViewName("dashboard");
            return modelAndView;
        } else
        {
            ModelAndView modelAndView = new ModelAndView();
            User user = new User();
            modelAndView.addObject("user", user);
            modelAndView.addObject("fullName", new String());
            modelAndView.setViewName("signup");
            return modelAndView;
        }
    }

}
