package com.stokkur.am.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jacobo
 */
public class InvalidTokenException extends RuntimeException {
    private final Logger logger = LoggerFactory.getLogger(InvalidTokenException.class);

    public InvalidTokenException(String message) {
        super("Invalid token: " + message);
        
        logger.error(message);
    }
}
