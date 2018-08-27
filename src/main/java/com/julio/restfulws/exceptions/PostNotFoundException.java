package com.julio.restfulws.exceptions;

//@ResponseStatus(HttpStatus.NOT_FOUND)
// Only works if you dont use ControllerAdvice to override response of RuntimeException or Exception
// Return the default error of SpringMVC
public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String message) {
        super(message);
    }

}
