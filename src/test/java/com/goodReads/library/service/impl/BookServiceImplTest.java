package com.goodReads.library.service.impl;

import com.goodReads.library.Repositry.BookRepository;
import com.goodReads.library.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    List<Book> bookList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        System.out.println("in the start");
        Book book = new Book();
        bookList.add(book);
    }


    @AfterEach()
    public void atTheLastOfEveryTestCase() {
        System.out.println("after each test");
    }

    @Test
    void getAllBooks() {
        BookServiceImpl bookService = new BookServiceImpl();// creating an object of bookService, It creates an instance of BookServiceImpl, which is the class under test.
        //mocking
        BookRepository bookRepository = mock(BookRepository.class);// this is same as "@Mock BookRepository bookRepository;"
        // this means whenever we see a BookRepository we can assume that an object is created for it by mock, technique of using BookRepository test double so service class can
        // interact with that instead of prod DAO which interacts with db
        // for eg in getAllBooks() method of BookServiceImpl the bookRepository is mock for BookRepository
//If there are 3 services, service1 depends on service2 and service2 depends on service3 if I mock service1 mockito will take care of functionalities of services 2,3
// which communicate with db.
        bookService.setBookRepository(bookRepository);// We are assigning the mocked bookRepository created in above line to the bookService. Though the mock is there before it's not in bookService so setBookRepository method is created in bookService and now this
        // can be used on my mocked bookRepository, now if we run getAllBooks() in test class the mocked bookRepository is assigned for the  BookServiceImpl one
        //stubbing  Stub -> we mock the behavior of the object when the methods are called.
        // *   ex: for bookService the mocked bookRepository, get a custom output of specific method.
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        bookList.add(book);
        when(bookRepository.findAll()).thenReturn(bookList);// when bookRepository.findAll() is called then return bookList
        bookService.getAllBooks();
    }

    @Test
    public void testGetAllBooks_EmptyTitle() {// you don't want to show a book where book title is empty
        BookServiceImpl bookService = new BookServiceImpl();// instead of @injectMock use this
        //mocking we mock the object here in the test case, we mock a class like
        BookRepository bookRepository = mock(BookRepository.class);// this means whenever we see a BookRepository we can assume that an object is created for it by mock,
        // for eg in getAllBooks() method of BookServiceImpl the bookRepository is mock for BookRepository
        bookService.setBookRepository(bookRepository);// setBookRepository is my mock repository

        //stubbing  Stub -> we mock the behavior of the object when the methods are called.

        // *   ex: for bookService the mocked bookRepository, get a custom output of specific method.
        List<Book> bookList = new ArrayList<>();
//        Book book = new Book();
//        bookList.add(book);
        when(bookRepository.findAll()).thenReturn(bookList);
        assertThrows(IllegalStateException.class, () -> bookService.getAllBooks());//In this case, IllegalStateException.class indicates that the assertion expects that Exception class to be
//thrown. If any other type of exception is thrown during the execution of the lambda expression, the assertion will fail.
//()-> signifies the start of a lambda expression with no parameters
// bookService.getAllBooks() is the body of the lambda expression. It represents a call to the getAllBooks() method of the bookService.

    }

    @Test
    public void testAddBook() {
        BookServiceImpl bookService = new BookServiceImpl();
        BookValidation bookValidation = mock(BookValidation.class);

        Book book = spy(new Book());
        // book.setTitle("PQR");
        bookService.setBookValidation(bookValidation);
        when(bookValidation.validateBook(any(Book.class))).thenReturn(true);// here we assume that for any parameter passed in book class that it will return true
        //if it's true then only it will go to next step
        //any(Book.class) is Mockito's way of specifying that any instance of the Book class can be passed
        // as an argument to the validateBook method of the bookValidation mock object.
//        Mockito will match any argument of type Book when validateBook is called during the test, regardless of its specific content.
//        This is particularly useful when you want to set up mock behavior for methods that take parameters but you're not interested in the exact value of those parameters for
//        the behavior you're defining. You're essentially saying, "Regardless of what Book object is passed to validateBook, always return true.
        when(book.getTitle()).thenReturn("PQR");
        // bookService.addBook(book);
        BookRepository bookRepository = mock(BookRepository.class);
        bookService.setBookRepository(bookRepository);

        bookService.addBook(book);
    }
    @Test
    public void testAddBook_InvalidBook() {
        BookServiceImpl bookService = new BookServiceImpl();
        BookValidation bookValidation = mock(BookValidation.class);
        Book book = new Book();
        bookService.setBookValidation(bookValidation);
        when(bookValidation.validateBook(any(Book.class))).thenReturn(false);
        // bookService.addBook(book);
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(book));
    }

    @Test
    public void testAddBook_InvalidBookWithSpy() {
        BookServiceImpl bookService = spy(new BookServiceImpl()); //new BookServiceImpl();Spy  -> To help stubbing when the object is not mocked.
// *-Ex: for non-mocked objects, we can spy for them to call a method which are stubbed and give stubbed output to them.
//In this test, you're using a spy for BookServiceImpl because you want to partially mock the real implementation of BookServiceImpl. By spying on BookServiceImpl, you can stub
// specific methods of the real BookServiceImpl object while allowing other methods to be called as usual. In this case, you're spying on BookServiceImpl to mock
// the behavior of validateBook() method through bookValidation.
        BookValidation bookValidation = spy(new BookValidation());// we use spy when we want to use methods of child class
        ValidationService validationService = new ValidationService();
        bookValidation.setValidationService(validationService);

        Book book = new Book();
        bookService.setBookValidation(bookValidation);
        doReturn(false).when(bookValidation).validateBook(any(Book.class));
        // when(bookValidation.validateBook(book)).thenReturn(false);
        // bookService.addBook(book);
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(book));
    }
    @Test
    public void testAddBook_WithABCTitle() {
        BookServiceImpl bookService = new BookServiceImpl();
        BookValidation bookValidation = mock(BookValidation.class);

        Book book = new Book();
        book.setTitle("ABC");
        bookService.setBookValidation(bookValidation);
        when(bookValidation.validateBook(any(Book.class))).thenReturn(true);
        // bookService.addBook(book);
        assertThrows(IllegalStateException.class, () -> bookService.addBook(book));
    }


}