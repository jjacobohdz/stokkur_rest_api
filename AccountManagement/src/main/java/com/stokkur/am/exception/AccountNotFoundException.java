package com.stokkur.am.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jacobo
 */
public class AccountNotFoundException extends RuntimeException {
    private final Logger logger = LoggerFactory.getLogger(InvalidTokenException.class);

    public AccountNotFoundException(Long id) {
      super("Account not found with id: " + id);
      
      logger.error("Account not found with id: " + id);
    }
}
