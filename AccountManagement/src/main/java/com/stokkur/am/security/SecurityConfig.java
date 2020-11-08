package com.stokkur.am.security;

import com.stokkur.am.jwt.JwtConfigurer;
import com.stokkur.am.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * This class applies the custom JwtConfigurer as the application security config,
 * thus requiring the user to be authenticated prior to accessing protected resources
 * within the application.
 * 
 * @author Jacobo
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtHelper theJwtHelper;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/accounts/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/accounts/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/accounts/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(theJwtHelper));
    }
}