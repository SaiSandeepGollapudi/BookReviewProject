package com.goodReads.library.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
public ResponseEntity<?> handleNullPointerException(NullPointerException e){
        return new ResponseEntity<>("something went wrong please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {// <?> wildcard indicates that the response body can be of any type, map in this
        // case and String in above case
        // Create a map to store field errors, where the field name is the key and the error message is the value
        Map<String, String> errorsMap = new HashMap<>();

        // Iterate over each FieldError object in the list of field errors associated with the exception
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            // Extract the field name and default error message from the current FieldError object
            String fieldName = error.getField(); // Get the name of the field that has the validation error
            String errorMessage = error.getDefaultMessage(); // Get the default error message for the validation error

            // Add the field name and error message to the errorsMap
            errorsMap.put(fieldName, errorMessage);
        }

        // Create a ResponseEntity containing the errors map as the response body
        // Set the HTTP status to BAD_REQUEST to indicate that the client's request was malformed due to validation errors
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }




}
