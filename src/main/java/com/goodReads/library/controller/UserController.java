package com.goodReads.library.controller;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController { // APIs are inside the controller

    @Autowired
    BookService bookService; // Autowired BookService to interact with the business logic


    @PostMapping("{bookId}/review")
    //@RequestMapping(method = RequestMethod.POST,path = "{bookId}/review")
    public ResponseEntity<Book> addReview(@PathVariable("bookId") String bookId, @RequestBody Review review) {
      //addReview method doesn't return any data in the response body (it returns HttpStatus.CREATED without any additional data), so it may seem redundant to specify ResponseEntity<Book>.
      //but it's a common to use ResponseEntity even if the response body is empty, as it provides flexibility to add more data to the response body in the future if needed.
        try {
            if (review.getReviewer().isEmpty() || review.getComment().isEmpty() || review.getRating()<= 0) {
                throw new IllegalArgumentException("Invalid review data");
            }
            bookService.addReview(bookId, review);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/book/author")// anything inside {id} is  part of url, it's given dynamically by user.
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam("author") String bookAuthor){// @PathVariable annotation maps dynamic id value from the URL path to bookId parameter in method signature.
        // The name bookId is used here to indicate that it represents the ID of book being requested. However, you could have named it id as well, and it would still work the same way.
        try {
            return new ResponseEntity<>(bookService.getBooksByAuthor(bookAuthor), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/book/year")
    public ResponseEntity<List<Book>> getBooksByYear(@RequestParam("year") Integer year) {
        try {
            return new ResponseEntity<>(bookService.getBooksByYear(year), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/book")
    public ResponseEntity<Set<Book>> getAllBooks() {
        try {
            // Retrieve all books from the service layer and return as a response with HTTP status OK
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/book/rating")// anything inside {rating} is  part of url, it's given dynamically by user.
    public ResponseEntity<List<Book>> getBooksByRating(@RequestParam("rating") Double bookRating){// @PathVariable mapping id in URL to parameter
        try {

            return new ResponseEntity<>(bookService.getBooksByRating(bookRating), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }    }

    @GetMapping("/book/genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") Genre genre){
        try {

            return new ResponseEntity<>(bookService.getBooksByGenre(genre), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get a specific book by ID
    @GetMapping("/book/{id}")// anything inside {id} is  part of url, it's given dynamically by user.
    public ResponseEntity<Book> getBook(@PathVariable("id") String bookId){// @PathVariable annotation maps dynamic id value from the URL path to bookId parameter in method signature.
        // The name bookId is used here to indicate that it represents the ID of book being requested. However, you could have named it id as well, and it would still work the same way.
        try {
            return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{bookId}/review")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable("bookId") String bookId) {
        try {
            return new ResponseEntity<>(bookService.getReviewsByBookId(bookId), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }
}

//exception handling for post methods when admin
// in next class, will teach db, security, validations, where we can add validations for each field, eg check if the email is proper, if not throw 400, if any internal issues, handle 500
