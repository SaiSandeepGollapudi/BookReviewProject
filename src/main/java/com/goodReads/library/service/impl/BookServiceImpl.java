package com.goodReads.library.service.impl;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.*;
@Service
public class BookServiceImpl implements BookService {

//    @Autowired
//        //    DummyService dummyService;
//    // HashMap to store Book objects with their IDs as keys
@Autowired
//@Qualifier("mysqlDatabaseConnection")// As we used Qualifier for both connections we can call a specific connection by its name mysqlDatabaseConnection or oracleDatabaseConnection
private Connection MySqlconnection;

    //    @Autowired
    //    @Qualifier("oracleDatabaseConnection")
    //    private Connection oracleConnection;
Map<String, Book> bookMap = new HashMap<>();
    Map<String, Review> reviewMap = new HashMap<>();

    // Method to add a new Book to the library
    public void addBook(Book book) {
        // Generate a random ID for the book
        Integer Id = new Random().nextInt(1, 6);
        // Set the ID of the book
        book.setId(String.valueOf(Id));

// Creating a database connection using the getDatabase() method from a MySqlDBImpl instance
//        Connection connection = new MySqlDBImpl().getDatabase();

        // Add the book to the map with its ID as the key
        bookMap.put(book.getId(), book);
    }

    // Method to retrieve all books in the library
    public Set<Book> getAllBooks() {
        // Create a HashSet containing all Book objects from the map and return it
        return new HashSet<>(bookMap.values());
    }

    // Method to retrieve a book by its ID
    public Book getBook(String Id) {
        // Retrieve the book from the map by its ID or return null if not found
        return bookMap.getOrDefault(Id, null);
    }

    public List<Book> getBooksByRating(Double rating) {
        List<Book> books = new ArrayList<>();

        // Iterate over the values of bookMap
        for (Book book : bookMap.values()) {
            // Check if the book and its rating are not null, and if the rating matches the desired rating
            if (book != null && book.getRating() != null && book.getRating().equals(rating)) {
                // If conditions are met, add the book to the list
                books.add(book);
            }
        }

        return books;
    }


    @Override
    public List<Book> getBooksByAuthor(String bookAuthor) {
        // Create a list to store matching books
        List<Book> matchingBooks = new ArrayList<>();

        // Iterate over the values of bookMap
        for (Book book : bookMap.values()) {
            // Check if the book's author matches the given author
            if (book != null && book.getAuthor().equals(bookAuthor)) {
                // If a matching book is found, add it to the list
                matchingBooks.add(book);
            }
        }
        // Return the list of matching books
        return matchingBooks;
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        List<Book> booksByGenre = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getGenre() != null && book.getGenre().equals(genre)) {
                booksByGenre.add(book);
            }
        }
        return booksByGenre;
    }


//    @Override
//    public List<Book> getBooksByGenre(Genre genre) {
//        List<Book> booksByGenre = new ArrayList<>();
//        for (Book book : bookMap.values()) {
//            System.out.println("Book Genre "+ book.getGenre());
//            if (book.getGenre() != null && book.getGenre().equalsIgnoreCase(genre)) {
//                booksByGenre.add(book);
//            }
//        }
//        return booksByGenre;
//    }

    public List<Book> getBooksByYear(Integer year) {
        List<Book> booksByYear = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.getYear() != null && book.getYear().equals(year)) {
                booksByYear.add(book);
            }
        }
        return booksByYear;
    }

    @Override
    public List<Review> getReviewsByBookId(String bookId) {
        // Retrieve the book from the map by its ID
        Book book = bookMap.getOrDefault(bookId, null);
        if (book != null) {
            // Return the review list of the book
            return book.getReviewList();

        }
        return null;
    }


    // Method to delete a book by its ID
    public void deleteBook(String id) {
        // Remove the book from the map by its ID
        bookMap.remove(id);
    }

    // Method to update a book's information
    public Book updateBook(String Id, Book book) {
        // Check if the book with the specified ID exists in the map
        if (bookMap.containsKey(Id)) {
            // If the book exists, update its information in the map
            bookMap.put(Id, book);
        }
        // Return the updated book
        return book;
    }



    // Method to add a review to a book
    @Override
    public void addReview(String bookId, Review review) {
        // Retrieve the book from the map by its ID
        Book book = bookMap.getOrDefault(bookId, null);
        // If the book exists
        if (book != null) {
            // Add the review to the book's review list
            book.getReviewList().add(review);
        }
        // Update the book in the map
        bookMap.put(bookId, book);

       Book book1= bookMap.get(bookId);
        List<Review> reviewList= book1.getReviewList();
        List<Double> ratings= new ArrayList<Double>();
        for(Review review1: reviewList){

             ratings.add(review1.getRating());
        }
        Double ratingSum=0.0;
        for(Double rating2: ratings){
            ratingSum += rating2;
        }
        Double avgRating=ratingSum/ratings.size();
        book1.setRating(avgRating);
    }


//    public void validateBook(Book book){
//        dummyService.validate(book);
//    }




}
