package com.stokkur.am.controller;

import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jacobo
 */
@RestController
public class UserController {
    
    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails aUserDetails){
        Map<Object, Object> model = new HashMap<>();
        
        model.put("username", aUserDetails.getUsername());
        model.put("roles", aUserDetails.getAuthorities()
            .stream()
            .map(a -> ((GrantedAuthority) a).getAuthority())
            .collect(toList())
        );
        
        return ok(model);
    }    
}
