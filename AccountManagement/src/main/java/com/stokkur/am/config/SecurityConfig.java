package com.stokkur.am.config;

import com.stokkur.am.config.JwtConfig;
import com.stokkur.am.exception.CustomFilterChainExceptionHandler;
import com.stokkur.am.jwt.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class applies the custom JwtConfig as the application security config,
 thus requiring the user to be authenticated prior to accessing protected resources
 within the application.
 * 
 * @author Jacobo
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtHelper theJwtHelper;
    
    @Autowired
    private CustomFilterChainExceptionHandler theFilterChainExceptionHandler;
    
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
                .antMatchers(HttpMethod.GET, "/api/accounts/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/accounts/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/accounts/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/accounts/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(theFilterChainExceptionHandler, UsernamePasswordAuthenticationFilter.class)
            .apply(new JwtConfig(theJwtHelper));
    }
}
