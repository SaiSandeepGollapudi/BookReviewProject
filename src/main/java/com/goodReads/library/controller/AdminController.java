package com.goodReads.library.controller;

import com.goodReads.library.domain.Book;
import com.goodReads.library.service.BookService;
import com.goodReads.library.service.resource.BookRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    BookService bookService;

    @PostMapping("/admin/book")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookRequest bookRequest){
        bookService.addBook(bookRequest.getBook());
        return  new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("admin/book")//PUT is used to update or replace an existing resource.
//    PUT does not create a new resource if it's not already present; it typically returns an error if the resource does not exist.
    public ResponseEntity<Book> updateBook(@RequestParam("bookId") Integer id,@RequestBody @Valid BookRequest bookRequest){
        if (!bookService.bookExists(id)) {
            // Return a response with HTTP status NOT_FOUND if the book does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.updateBook(id, bookRequest.getBook()),HttpStatus.OK);

    }



   //  Endpoint to delete an existing book
    @DeleteMapping("/admin/book")
    //@RequestParam annotation enables Spring to extract input data that may be passed as a query
    public ResponseEntity<Book> deleteBook(@RequestParam("bookId") Integer id) {
        //ex: /admin/book?bookId=1, Spring has rule that if in ur url there is variable bookId then is same as the
        // variable name it can automatically map it
        try {
            if (!bookService.bookExists(id)) {
                // Return a response with HTTP status NOT_FOUND if the book does not exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        bookService.deleteBook(id);
        // Return a response with HTTP status NO_CONTENT
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @Autowired
//    BookService bookService; // Autowired BookService to interact with the business logic
//   // DummyServiceImpl dummyService;
//
////    @Autowired
////    DBService dBService;
//    //When you can't make decision which type of injection to use go for setter injection
//    // The first time dependency injection is done by Spring, second time. We can change the implementation of service at run time using setter injection
////    @Autowired// Set type help when we want to change implementation at run time /compile time. For eg, we want to test the payment service,
////    // then we will inject dummyService during test time and in prod environment we will use org service
////    public void setDummyService(DummyServiceImpl dummyService){
////        this.dummyService=dummyService;
////    }
//    //        bookService.setDummyService(new DummyServiceTwo()); //Anytime I want another dependency, I can change it for every request, we can do that with set injections
////        bookService.validate(bookService.getBook(id));
//
////    @Autowired
////    public AdminController(BookService bookService, DummyServiceImpl dummyService){//A controller class can have many dependencies like greetService, bookService, etc
////
////        this.bookService=bookService;
////        //this.dummyService=dummyService;
////    }
//
//    // Endpoint to create a new book
//   // @ResponseStatus
//    @PostMapping("/admin/book")
//    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {//When a POST request is sent to the specified endpoint ("/admin/book"),  HTTP request contains data in its body.
//        // The @RequestBody annotation is used to bind the HTTP request body to a method parameter.
//        // In this case, it binds the request body to the book parameter of the createBook method.
//        try {
//            if (book.getTitle().isEmpty()|| book.getAuthor().isEmpty() || book.getGenre()==null) {
//                throw new IllegalArgumentException("Invalid book data");// It's thrown to signify that the values of the arguments passed to a method are not valid,
//                // either because they are outside the range of acceptable values or they violate some other constraint.
//                // general-purpose exception in Java used to indicate invalid method arguments, regardless of the framework being used
//            }
//
//            if (book.getCost() <= 0) {
//                throw new IllegalArgumentException("Cost cannot be negative");
//            }
//
//            if (book.getYear() <= 0 ) {
//                throw new IllegalArgumentException("Invalid year");
//            }
//            bookService.addBook(book);
//            // Return a response with HTTP status CREATED
//            return new ResponseEntity<>(HttpStatus.CREATED); // You can create ResponseEntity objects with specific HTTP status
//            // codes, headers, and bodies to tailor the response according to your application's requirements
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Endpoint to update an existing book
//    @PutMapping("/admin/book")
//    //ex: /admin/book?bookId=1
//    // @RequestParam("paramName") indicates that the value of the query parameter named "bookId" will be extracted from the request URL.
//   // String paramValue is the method parameter where the value of the query parameter will be bound.//ex: /admin/book/?bookId=1.
//    public ResponseEntity<Book> updateBook(@RequestParam("bookId") String id, @RequestBody Book book) {
////including the updated book object (bookService.updateBook(id, book)) in the response body. This allows the client to receive the updated representation of the resource
//// after the update operation.
//        try {
////            if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getGenre().isEmpty()) {
////                throw new IllegalArgumentException("Invalid book data");
////            }
//            if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getGenre() == null) {
//                throw new IllegalArgumentException("Invalid book data");
//            }
//            if (book.getCost() <= 0) {
//                throw new IllegalArgumentException("Cost cannot be negative");
//            }
//
//            if ((book.getYear() <= 0)) {
//                throw new IllegalArgumentException("Invalid year");
//            }
//            return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
//        }
//    catch (IllegalArgumentException e) {
//        // If invalid book data is provided, return a response with HTTP status BAD_REQUEST
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}
//
//
}
