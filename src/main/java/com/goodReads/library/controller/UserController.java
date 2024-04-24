package com.goodReads.library.controller;

import com.goodReads.library.domain.User;
import com.goodReads.library.service.UserService;
import com.goodReads.library.service.resource.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService; // Autowired UserService to interact with the business logic

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest userRequest){
        userService.addUser(userRequest.getUser());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PutMapping("/user")//PUT is used to update or replace an existing resource.
//    PUT does not create a new resource if it's not already present; it typically returns an error if the resource does not exist.
    public ResponseEntity<User> updateUser(@RequestParam("userId") Integer id, @RequestBody @Valid UserRequest userRequest){
        if (!userService.userExists(id)) {
            // Return a response with HTTP status NOT_FOUND if the book does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userService.updateUser(id, userRequest.getUser()),HttpStatus.OK);
    }
//
//
//
    //  Endpoint to delete an existing book
    @DeleteMapping("/user")
    //@RequestParam annotation enables Spring to extract input data that may be passed as a query
    public ResponseEntity<User> deleteBook(@RequestParam("userId") Integer id) {
        //ex: /admin/book?bookId=1, Spring has rule that if in ur url there is variable bookId then is same as the
        // variable name it can automatically map it

            if (!userService.userExists(id)) {
                // Return a response with HTTP status NOT_FOUND if the book does not exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.deleteUser(id);
            // Return a response with HTTP status NO_CONTENT
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
