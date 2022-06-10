package com.nnk.springboot.configuration;

import com.nnk.springboot.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private final AuthSuccessHandler authSuccessHandler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          AuthSuccessHandler authSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/css/**").permitAll()
                .antMatchers("/admin/**", "/user/**", "/secure/**").hasAuthority(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(authSuccessHandler)
                .and()
                .oauth2Login().loginPage("/login")
                .successHandler(authSuccessHandler)
                .and()
                .logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

}