package com.nnk.springboot.configuration;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String userInfo = getUserInfo(authentication);
        String redirectUrl = "";
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        for(GrantedAuthority authority: userAuthorities) {
            if(authority.getAuthority().contains("ADMIN")) {
                redirectUrl = "/user/list?userInfo="+userInfo;
            } else {
                redirectUrl = "/bidList/list?userInfo="+userInfo;
            }
        }
        response.sendRedirect(redirectUrl);
    }

    public String getUserInfo(Principal user) {
        StringBuffer userInfo = new StringBuffer();
        if(user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUsernamePasswordLoginInfo(user));
        } else if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOAuth2LoginInfo(user));
        }
        return userInfo.toString();
    }

    private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
        StringBuffer usernameInfo = new StringBuffer();
        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()) {
            User u = (User) token.getPrincipal();
            usernameInfo.append(u.getUsername());
        } else {
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

    private StringBuffer getOAuth2LoginInfo(Principal user) {
        StringBuffer protectedInfo = new StringBuffer();
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        if(authToken.isAuthenticated()) {
            Map<String, Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
            protectedInfo.append(userAttributes.get("name"));
        }
        return protectedInfo;
    }

}