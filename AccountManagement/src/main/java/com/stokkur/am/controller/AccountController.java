package com.stokkur.am.controller;

import com.stokkur.am.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is intended to authenticate the user
 * 
 * @author Jacobo
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountRepository theAccountRepository;
    
    @GetMapping("")
    public ResponseEntity all() {
        return ok(theAccountRepository.findAll());
    }
}
