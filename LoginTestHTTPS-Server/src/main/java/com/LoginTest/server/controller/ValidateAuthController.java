package com.LoginTest.server.controller;

import com.LoginTest.server.model.User;
import com.LoginTest.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateAuthController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService us;

    @PostMapping(value="/checkuser/{email}", consumes="application/json", produces="application/json")
    public ResponseEntity<HttpStatus> validateAuth(@PathVariable("email") String email, @RequestBody User user)
    {
        if(user==null || email==null)
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        else {
            User user1 = us.getUserByEmail(email);
            if (user1.getEmail().equals(user.getEmail()))
                if (passwordEncoder.matches(user.getPassword(),user1.getPassword()))
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                else
                    return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
            else
                return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
