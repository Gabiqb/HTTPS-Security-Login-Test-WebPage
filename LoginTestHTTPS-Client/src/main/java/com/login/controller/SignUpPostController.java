package com.login.controller;

import com.login.misc.Roles;
import com.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignUpPostController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("fullName") String name, User user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        HttpStatus httpStatus = restTemplate.getForObject("https://localhost:8082/" + user.getEmail(), HttpStatus.class);
        if (httpStatus == HttpStatus.ACCEPTED) {
            user.setName(name);
            user.getRoles().add(Roles.USER);
            restTemplate.postForObject("https://localhost:8082/signup/" + user.getEmail(), user, User.class);
            modelAndView.addObject("successMessage", "User has been registered succesfully :)");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        } else {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("signup");
            }
        }
        return modelAndView;
    }
}
