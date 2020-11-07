package com.stokkur.am.exception;

/**
 *
 * @author Jacobo
 */
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
      super("Account not found with id: " + id);
    }
}
