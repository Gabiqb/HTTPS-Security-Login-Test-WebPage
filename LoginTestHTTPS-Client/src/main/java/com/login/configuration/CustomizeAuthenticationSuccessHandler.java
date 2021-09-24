package com.login.configuration;

import com.login.misc.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException{
        //Currently only auths the users with the role : USER
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ((Roles.USER.toString()).equals(auth.getAuthority())) {
                response.sendRedirect("/dashboard");
            }
        }
    }
}

