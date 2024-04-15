package com.goodReads.library.controller;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController { // APIs are inside the controller

    @Autowired
    BookService bookService; // Autowired BookService to interact with the business logic

    // Endpoint to get all books
    @GetMapping("/book")
    public ResponseEntity<Set<Book>> getAllBooks() {
        // Retrieve all books from the service layer and return as a response with HTTP status OK
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    // Endpoint to get a specific book by ID
    @GetMapping("/book/{id}")// anything inside {id} is  part of url, it's given dynamically by user.
    public ResponseEntity<Book> getBook(@PathVariable("id") String bookId){// @PathVariable mapping id in URL to parameter
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    // Endpoint to add a review for a specific book
    @PostMapping("{bookId}/review")
    //@RequestMapping(method = RequestMethod.POST,path = "{bookId}/review")
    public ResponseEntity<Book> addReview(@PathVariable("bookId") String bookId, @RequestBody Review review) {
        // Add the review for the specified book using the service layer
        bookService.addReview(bookId, review);
        // Return a response with HTTP status CREATED
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
