package com.stokkur.am.exception;

/**
 *
 * @author Jacobo
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
      super("Invalid token: " + message);
    }
}
