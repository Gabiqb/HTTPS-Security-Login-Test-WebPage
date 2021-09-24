package com.LoginTest.server.controller;

import com.LoginTest.server.model.User;
import com.LoginTest.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnUserController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @RequestMapping("/get/{email}")
    public User returnUser(@PathVariable("email") String email)
    {
        if(userService.getUserByEmail(email)!=null)
            return userService.getUserByEmail(email);
        else
            return null;
    }
}
