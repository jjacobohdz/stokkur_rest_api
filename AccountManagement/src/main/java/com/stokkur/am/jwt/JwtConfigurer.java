package com.stokkur.am.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is intended to setup the JWT Filter, and it is plugged-in by the Application SecurityConfig.
 * It works together with the application's custom WebSecurityConfigurerAdapter (SecurityConfig) to
 * enable HTTP Basic and Form based authentication.
 * 
 * @author Jacobo
 */
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtHelper theJwtHelper;
    
    public JwtConfigurer(JwtHelper aJwtHelper) {
        theJwtHelper = aJwtHelper;
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter aJwtFilter = new JwtFilter(theJwtHelper);
        
        http.addFilterBefore(aJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
