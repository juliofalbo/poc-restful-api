package com.julio.restfulws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
// Only works if you dont use ControllerAdvice to override response of RuntimeException or Exception
// Return the default error of SpringMVC
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
