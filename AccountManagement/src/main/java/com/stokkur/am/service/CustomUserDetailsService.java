package com.stokkur.am.service;

import com.stokkur.am.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of UserDetailsService to retrieve user related data.
 * 
 * @author Jacobo
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    
    @Autowired(required = false)
    private UserRepository theUserRepository;
    
    /**
     * Customize the process of finding a user.
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("retrieving user with name" + username);
        
        return theUserRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("username: " + username + " not found"));
    }    
    
}
