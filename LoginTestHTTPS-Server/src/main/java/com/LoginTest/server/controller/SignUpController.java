package com.LoginTest.server.controller;

import com.LoginTest.server.model.User;
import com.LoginTest.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup/{email}", method= RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
}
