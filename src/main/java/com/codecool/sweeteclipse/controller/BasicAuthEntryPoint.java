package com.codecool.sweeteclipse.controller;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BasicAuthEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void afterPropertiesSet() {
        setRealmName("Agaclipsia");
        super.afterPropertiesSet();
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        // the super sets the response status to HTTP 401 (we have an authException and auth has failed)
        // it also sets the response header's 'WWW-Authenticate' field to our realm name
        // If we want something more complex, we could override this method in earnest
        super.commence(request, response, authException);
    }
}
