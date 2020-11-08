package com.stokkur.am.jwt;

import com.stokkur.am.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Helper class to provide JWT functionality such as token creation/validation and claims parsing
 * 
 * @author Jacobo
 */
@Component
public class JwtHelper {
    
    @Value("${security.jwt.token.secret-key:secret}")
    private String theSecretKey = "5ecr3t";

    // granted token is valid for one hour    
    @Value("${security.jwt.token.expire-length:3600000}")
    private final long VALIDITY_IN_MILLIS = 3600000;
    
    private final static String ROLES = "roles";
    private final static String AUTHORIZATION = "Authorization";
    private final static String BEARER = "Bearer ";
    
    private final static int BEARER_START_INDEX = BEARER.length();
    
    @Autowired
    private CustomUserDetailsService theUserDetailsService;
    
    @PostConstruct
    protected void init() {
        theSecretKey = Base64.getEncoder().encodeToString(theSecretKey.getBytes());
    }
    
    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(ROLES, roles);
        
        Date startValidity = new Date();
        Date endValidity = new Date(startValidity.getTime() + VALIDITY_IN_MILLIS);
        
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(startValidity)
            .setExpiration(endValidity)
            .signWith(SignatureAlgorithm.HS256, theSecretKey)
            .compact();
    }
    
    public Authentication getUsernamePasswordAuth(String token) {
        UserDetails userDetails = this.theUserDetailsService.loadUserByUsername(getUsername(token));
        
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(theSecretKey).parseClaimsJws(token).getBody().getSubject();
    }
    
    public String parseJwtToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        
        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(BEARER_START_INDEX, bearerToken.length());
        }
        
        return null;
    }
    
    public boolean isValidJwtToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(theSecretKey).parseClaimsJws(token);
            
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Invalid JWT token", e.getCause());
        }
    }
    
}