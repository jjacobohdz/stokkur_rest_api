package com.stokkur.am.service;

import com.stokkur.am.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of CustomUserDetailsService to retrieve user related data.
 * 
 * @author Jacobo
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository theUserRepository;
    
    public CustomUserDetailsService(UserRepository aUserRepository) {
        theUserRepository = aUserRepository;
    }

    /**
     * Customize the process of finding a user.
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return theUserRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("username: " + username + " not found"));
    }    
    
}
