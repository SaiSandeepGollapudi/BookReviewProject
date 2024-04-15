package com.goodReads.library.service;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Review;

import java.util.Set;

public interface BookService {

    public void addBook(Book book);
    public Set<Book> getAllBooks();
    public Book getBook(String Id);

    public void deleteBook(String Id);
    public Book updateBook(String Id, Book book);

    public void addReview(String bookId, Review review);

}
