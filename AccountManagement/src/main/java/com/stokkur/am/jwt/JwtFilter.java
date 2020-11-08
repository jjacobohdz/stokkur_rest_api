package com.stokkur.am.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * This class is a custom JWT token based authentication filter.
 * 
 * @author Jacobo
 */
public class JwtFilter extends GenericFilterBean {
    
    private final JwtHelper theJwtHelper;
    
    public JwtFilter(JwtHelper aJwtHelper) {
        this.theJwtHelper = aJwtHelper;
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException {
        String aJwtToken = theJwtHelper.parseJwtToken((HttpServletRequest) req);
        
        if (aJwtToken != null && theJwtHelper.isValidJwtToken(aJwtToken)) {
            Authentication auth = theJwtHelper.getUsernamePasswordAuth(aJwtToken);
            
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        filterChain.doFilter(req, res);
    }
}