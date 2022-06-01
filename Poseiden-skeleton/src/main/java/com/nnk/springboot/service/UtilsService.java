package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public final class UtilsService {

    public static String getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            if (authentication.isAuthenticated()) {
                User u = (User) authentication.getPrincipal();
                return u.getUsername();
            }
        } else if (authentication instanceof OAuth2AuthenticationToken) {
            if (authentication.isAuthenticated()) {
                OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) authentication);
                Map<String, Object> userAttributes = authToken.getPrincipal().getAttributes();
                return (String) userAttributes.get("name");
            }
        }
        return "Authenticated user";
    }

}