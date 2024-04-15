package com.goodReads.library.service.impl;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class BookServiceImpl implements BookService {

    // HashMap to store Book objects with their IDs as keys
    Map<String, Book> bookMap = new HashMap<>();

    // Method to add a new Book to the library
    public void addBook(Book book) {
        // Generate a random ID for the book
        Integer Id = new Random().nextInt(1, 3);
        // Set the ID of the book
        book.setId(String.valueOf(Id));
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
    }
}
