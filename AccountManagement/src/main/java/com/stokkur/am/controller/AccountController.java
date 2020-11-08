package com.stokkur.am.controller;

import com.stokkur.am.entity.Account;
import com.stokkur.am.exception.AccountNotFoundException;
import com.stokkur.am.hateoas.AccountModelAssembler;
import com.stokkur.am.repository.AccountRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This controller is intended to authenticate the user
 * 
 * @author Jacobo
 */
@RestController
@RequestMapping("/api")
public class AccountController {
    
    @Autowired
    private AccountRepository theAccountRepository;
    
    @Autowired
    private AccountModelAssembler theAccountModelAssembler;
    
    @GetMapping("/accounts")
    public CollectionModel<EntityModel<Account>> all() {

      List<EntityModel<Account>> accounts = theAccountRepository.findAll().stream()
          .map(theAccountModelAssembler::toModel)
          .collect(Collectors.toList());

      return CollectionModel.of(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
    }
    
    @GetMapping("/accounts/{id}")
    public EntityModel<Account> one(@PathVariable Long id) {

      Account account = theAccountRepository.findById(id)
          .orElseThrow(() -> new AccountNotFoundException(id));

      return theAccountModelAssembler.toModel(account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> newAccount(@RequestBody Account aNewAccount) {

      EntityModel<Account> entityModel = theAccountModelAssembler.toModel(theAccountRepository.save(aNewAccount));

      return ResponseEntity
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
          .body(entityModel);
    }
    
    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> replaceAccount(@RequestBody Account anAccount, @PathVariable Long id) {

      Account updatedAccount = theAccountRepository.findById(id)
          .map(account -> {
            account.setFirstName(anAccount.getFirstName());
            account.setLastName(anAccount.getLastName());
            account.setEmail(anAccount.getEmail());
            account.setBalance(anAccount.getBalance());
            account.setAge(anAccount.getAge());
            
            return theAccountRepository.save(account);
          })
          .orElseGet(() -> {
            anAccount.setId(id);
            return theAccountRepository.save(anAccount);
          });

      EntityModel<Account> entityModel = theAccountModelAssembler.toModel(updatedAccount);

      return ResponseEntity
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
          .body(entityModel);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {

      theAccountRepository.deleteById(id);

      return ResponseEntity.noContent().build();
    }

}
