package com.goodReads.library.controller;

import com.goodReads.library.domain.Book;
import com.goodReads.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    BookService bookService; // Autowired BookService to interact with the business logic

    // Endpoint to create a new book
    @PostMapping("/admin/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        // Add the book to the library using the BookService
        bookService.addBook(book);
        // Return a response with HTTP status CREATED
        return new ResponseEntity<>(HttpStatus.CREATED); // You can create ResponseEntity objects with specific HTTP status
        // codes, headers, and bodies to tailor the response according to your application's requirements
    }

    // Endpoint to update an existing book
    @PutMapping("/admin/book")
    public ResponseEntity<Book> updateBook(@RequestParam("bookId") String id, @RequestBody Book book) {
        // Update the book with the specified ID using the BookService
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    // Endpoint to delete an existing book
    @DeleteMapping("/admin/book")
    //@RequestParam annotation enables Spring to extract input data that may be passed as a query
    public ResponseEntity<Book> deleteBook(@RequestParam("bookId") String id) {
        //ex: /admin/book/?bookId=1, Spring has rule that if in ur url there is variable bookId then is same as the
        // variable name it can automatically map it

        // Delete the book with the specified ID using the BookService
        bookService.deleteBook(id);
        // Return a response with HTTP status NO_CONTENT
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
