package com.goodReads.library.Exception;

public class UserAlreadyExistsException extends RuntimeException {
    // when you create a custom exception class that extends another exception class it's common practice to call the constructor
    // of the superclass (i.e., the parent exception class) using the super keyword.
    public UserAlreadyExistsException(String message) {
        super(message);//super(message) is calling the constructor of the RuntimeException class with a message parameter. This allows you to provide a custom error
        // message when an instance of UserAlreadyExistsException is created.
    }
}
