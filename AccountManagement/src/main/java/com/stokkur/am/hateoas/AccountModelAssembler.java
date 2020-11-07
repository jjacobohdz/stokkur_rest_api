package com.stokkur.am.hateoas;

import com.stokkur.am.controller.AccountController;
import com.stokkur.am.entity.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 *
 * @author Jacobo
 */
@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {

  @Override
  public EntityModel<Account> toModel(Account account) {

    return EntityModel.of(account,
        linkTo(methodOn(AccountController.class).one(account.getId())).withSelfRel(),
        linkTo(methodOn(AccountController.class).all()).withRel("accounts"));
  }
}
