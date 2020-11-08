package com.stokkur.am.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jacobo
 */
@ControllerAdvice
public class InvalidTokenAdvice {
    
    @ResponseBody
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String invalidTokenHandler(InvalidTokenException ex) {
      return ex.getMessage();
    }
    
}
