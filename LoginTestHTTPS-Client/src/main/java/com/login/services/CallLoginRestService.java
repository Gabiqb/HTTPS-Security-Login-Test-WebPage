package com.login.services;


import com.login.model.UserPrincipal;
import com.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Component
public class CallLoginRestService implements UserDetailsService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public UserDetails loadUserByUsername(String email) {
        try{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String pass= request.getParameter("password");
        User user1=new User();
        user1.setEmail(email);
        user1.setPassword(pass);

            ResponseEntity<HttpStatus> httpStatus=restTemplate.postForEntity("https://localhost:8082/checkuser/"+email,user1,HttpStatus.class);
            if(httpStatus.getStatusCode()== HttpStatus.ACCEPTED)
            {
                User user = restTemplate.getForObject("https://localhost:8082/get/"+ email, User.class);
                UserPrincipal userPrincipal=new UserPrincipal(user);
                return userPrincipal;
            }
            else
            {
                throw new UsernameNotFoundException("Username not found");
            }
        }
        catch(Exception e)
        {
            System.out.println("Check web-page");
            return new UserPrincipal(new User());
        }

    }

}

