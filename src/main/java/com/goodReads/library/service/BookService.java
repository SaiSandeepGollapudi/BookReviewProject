package com.goodReads.library.service;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import com.goodReads.library.domain.Review;

import java.util.List;

public interface BookService {


    public void addBook(Book book);
    public List<Book> getAllBooks();
    public Book getBook(Integer Id);
    public List<Book> getBooksByAuthor(String author);
    public List<Book> getBooksByTitle(String title);
    public List<Book> getBooksByAuthorAndGenre(String author, Genre genre);


    public List<Book> getBooksByTitleLike(String title);


    public void deleteBook(Integer Id);
    public Book updateBook(Integer Id, Book book);

    public void addReview(String bookId, Review review);

//    public void addBook(Book book);
//    public Set<Book> getAllBooks();
//    public Book getBook(String Id);
//
//
//    public void deleteBook(String Id);
//    public Book updateBook(String Id, Book book);
//
//    public void addReview(String bookId, Review review);
//
//
//
  //  public List<Book> getBooksByRating(Double rating);
//
//    public List<Book> getBooksByAuthor(String bookAuthor);
//
    public List<Book> getBooksByGenre(Genre genre);

//
    public List<Book> getBooksByYear(Integer year);
//
//    public List<Review> getReviewsByBookId(String bookId);
}
