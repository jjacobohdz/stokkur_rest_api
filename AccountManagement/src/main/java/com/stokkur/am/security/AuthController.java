package com.stokkur.am.security;

import com.stokkur.am.jwt.JwtHelper;
import com.stokkur.am.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jacobo
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager theAuthenticationManager;
    
    @Autowired
    private JwtHelper theJwtHelper;
    
    @Autowired
    private UserRepository theUsersRepository;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest authRequest) {
        try {
            String username = authRequest.getUsername();
            theAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequest.getPassword()));
            
            String aJwtToken = theJwtHelper.createToken(username, theUsersRepository.findByUsername(username).
                    orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found")).getRoles());
            
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", aJwtToken);
            
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }
}
