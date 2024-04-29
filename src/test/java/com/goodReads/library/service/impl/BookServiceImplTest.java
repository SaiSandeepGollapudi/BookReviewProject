package com.goodReads.library.service.impl;

import com.goodReads.library.Repositry.BookRepository;
import com.goodReads.library.domain.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Test
    void getAllBooks() {
        BookServiceImpl bookService= new BookServiceImpl();
        //mocking
        BookRepository bookRepository= mock(BookRepository.class);// this means whenever we see a BookRepository we can assume that an object is created for it by mock,
        // for eg in getAllBooks() method of BookServiceImpl the bookRepository is mock for BookRepository
        bookService.setBookRepository(bookRepository);// setBookRepository is my mock repository

        //stubbing  Stub -> we mock the behavior of the object when the methods are called.

        // *   ex: for bookService the mocked bookRepository, get a custom output of specific method.
        List<Book> bookList= new ArrayList<>();
        Book book = new Book();
        bookList.add(book);
        when(bookRepository.findAll()).thenReturn(bookList);
        bookService.getAllBooks();
    }

}