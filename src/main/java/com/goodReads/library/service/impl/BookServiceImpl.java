package com.goodReads.library.service.impl;

import com.goodReads.library.Repositry.BookRepository;
import com.goodReads.library.Repositry.ReviewRepository;
import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import com.goodReads.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {

//    @Autowired
//        //    DummyService dummyService;
//    // HashMap to store Book objects with their IDs as keys

    @Autowired
    ReviewRepository reviewRepository;
    BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

//    public void  addBook(Book book){
//
//        bookRepository.save(book);
//    }
@Autowired
BookValidation bookValidation;

    public void setBookValidation(BookValidation bookValidation){
        this.bookValidation=bookValidation;// for testing we use set injection
    }
public void addBook(Book book){

    if(!bookValidation.validateBook(book)){

        throw new IllegalArgumentException("Book is not valid");
    }

        if(book.getTitle().contains("ABC"))
    {
        throw new IllegalStateException("Title cannot be ABC");
    }

    bookRepository.save(book);
}
    public List<Book> getAllBooks(){
        List<Book> bookList=bookRepository.findAll();//bookRepository is mock for BookRepository i.e a dummy object for bookRepository which was created in test class
        // it gives empty output, i.e. an object with all values as null, it doesn't go to db
        if(bookList.isEmpty()){
            throw new IllegalStateException("books list cannot be empty");// so because of above dummy object we got an exception, so one scenario is working getAllBooks()
        }

        return bookList;// now one object with all values as null is created so this test passed as well
    }



    //    public List<Book> getAllBooks(){
//
//        return bookRepository.findAll();
//    }
    public List<Book> getBooksByAuthor(String author){

        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByTitle(String title){

        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByTitleLike(String title){

        return bookRepository.findByTitleIgnoreCaseContaining(title);
    }


    public List<Book> getBooksByYear(Integer year){

        return bookRepository.findByYear(year);
    }

    public Book getBook(Integer Id){

        return bookRepository.findById(Id).orElse(null);
    }
    public void deleteBook(Integer id){

        bookRepository.deleteById(id);
    }

    public boolean bookExists(Integer id) {
        return bookRepository.existsById(id);
    }

    @Override
    public boolean bookExistsByAuthor(String author) {
        return bookRepository.existsByAuthor(author);
    }

    @Override
    public boolean bookExistsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    @Override
    public boolean bookExistsByYear(Integer year) {
        return bookRepository.existsByYear(year);
    }

    @Override
    public boolean bookExistsByAuthorAndYear(String author, Integer year) {
        return bookRepository.existsByAuthorAndYear(author, year);
    }

    @Override
    public boolean bookExistsByGenre(Genre genre) {
        return bookRepository.existsByGenre(genre);
    }

    public List<Book> getBooksByGenre(Genre genre) {
          return bookRepository.findByGenre(genre);
      }

    public Book updateBook(Integer Id, Book updatedBookData) {
        Optional<Book> optionalOriginalBook = bookRepository.findById(Id);// retrieve the original book entity from the repository based on the provided Id using
        // the findById method of the bookRepository. The result is wrapped in an Optional to handle cases where the book may not exist.
//        The Optional<Book> type was introduced in Java 8  to deal with potentially null values in a safer and more concise manner
//Presence of Value:If a value of type Book is present, it is wrapped inside an Optional<Book> instance using the of() method or other factory methods provided by the Optional class.
// Absence of a Value: If the value is absent (i.e., null), an empty Optional<Book> instance is created using the empty() method.
//                Avoiding NullPointerExceptions:
//        Optional<Book> helps prevent NullPointerExceptions by providing methods to safely access and manipulate the value without directly exposing it.
//        Operations on Optional:
//        Optional<Book> provides various methods to perform operations on the wrapped value, such as get(), isPresent(), orElse(), orElseGet(), orElseThrow(), etc.
        if (optionalOriginalBook.isPresent()) { // Check if the original book entity exists in the repository
            // If the original book exists, update it with the provided book entity
            // This assumes that the book entity passed as an argument contains the updated data
            Book originalBook = optionalOriginalBook.get();
            originalBook.setTitle(updatedBookData.getTitle());
            originalBook.setAuthor(updatedBookData.getAuthor());
            originalBook.setRating(updatedBookData.getRating());
            originalBook.setGenre(updatedBookData.getGenre());
            originalBook.setCost(updatedBookData.getCost());
            originalBook.setYear(updatedBookData.getYear());
            return bookRepository.save(originalBook);
            }else {
            // Handle the case where the book with the provided ID does not exist
            return null;
        }
    }

    public List<Book> getBooksByAuthorAndGenre(String author, Genre genre){
        return bookRepository.findByAuthorAndGenre(author,genre);
    }

    public List<Book> getBooksByAuthorAndYear(String author, Integer year){
        return bookRepository.findByAuthorAndYear(author,year);
    }


    // Method to add a review to a book
//    @Override
//    public void addReview(Integer bookId, Review review) {
//        // Retrieve the book from the map by its ID
//        Book book = new Book();
//        Optional<Book> bookOptional=bookRepository.findById(review.getBook().getId());
//        if(bookOptional.isEmpty()){
//            throw new IllegalArgumentException("Book Id does not exist");
//        }
//        else {
//            // Add the review to the book's review list
//            book.getReviewList().add(review);
//        }
//
//        // Update the book in the map
//        review.setBook(bookOptional.get());
//        reviewRepository.save(review);
//
//        Optional<Book> bookOptional1= bookRepository.findById(bookId);
//        List<Review> reviewList= bookOptional1.getReviewList();
//        List<Double> ratings= new ArrayList<Double>();
//        for(Review review1: reviewList){
//
//            ratings.add(review1.getRating());
//        }
//        Double ratingSum=0.0;
//        for(Double rating2: ratings){
//            ratingSum += rating2;
//        }
//        Double avgRating=ratingSum/ratings.size();
//        book1.setRating(avgRating);
//    }

    // public List<Book> getBooksByRating(Double rating) {
//     return bookRepository.findBooksByRating(rating);
//
// }
//    @Override
//    public void addReview(String bookId, Review review) {
//       Book book=bookMap.getOrDefault(bookId,null);
//
//       if(book!=null)
//       {
//         //  book.getReviewList().add(review);
//       }
//       bookMap.put(bookId,book);
//    }
//
//@Autowired
////@Qualifier("mysqlDatabaseConnection")// As we used Qualifier for both connections we can call a specific connection by its name mysqlDatabaseConnection or oracleDatabaseConnection
//private Connection MySqlconnection;
//
//    //    @Autowired
//    //    @Qualifier("oracleDatabaseConnection")
//    //    private Connection oracleConnection;
//Map<String, Book> bookMap = new HashMap<>();
//    Map<String, Review> reviewMap = new HashMap<>();
//
//    // Method to add a new Book to the library
//    public void addBook(Book book) {
//        // Generate a random ID for the book
//        Integer Id = new Random().nextInt(1, 6);
//        // Set the ID of the book
//        book.setId(String.valueOf(Id));
//
//// Creating a database connection using the getDatabase() method from a MySqlDBImpl instance
////        Connection connection = new MySqlDBImpl().getDatabase();
//
//        // Add the book to the map with its ID as the key
//        bookMap.put(book.getId(), book);
//    }
//
//    // Method to retrieve all books in the library
//    public Set<Book> getAllBooks() {
//        // Create a HashSet containing all Book objects from the map and return it
//        return new HashSet<>(bookMap.values());
//    }
//
//    // Method to retrieve a book by its ID
//    public Book getBook(String Id) {
//        // Retrieve the book from the map by its ID or return null if not found
//        return bookMap.getOrDefault(Id, null);
//    }
//
//    public List<Book> getBooksByRating(Double rating) {
//        List<Book> books = new ArrayList<>();
//
//        // Iterate over the values of bookMap
//        for (Book book : bookMap.values()) {
//            // Check if the book and its rating are not null, and if the rating matches the desired rating
//            if (book != null && book.getRating() != null && book.getRating().equals(rating)) {
//                // If conditions are met, add the book to the list
//                books.add(book);
//            }
//        }
//
//        return books;
//    }
//
//
//    @Override
//    public List<Book> getBooksByAuthor(String bookAuthor) {
//        // Create a list to store matching books
//        List<Book> matchingBooks = new ArrayList<>();
//
//        // Iterate over the values of bookMap
//        for (Book book : bookMap.values()) {
//            // Check if the book's author matches the given author
//            if (book != null && book.getAuthor().equals(bookAuthor)) {
//                // If a matching book is found, add it to the list
//                matchingBooks.add(book);
//            }
//        }
//        // Return the list of matching books
//        return matchingBooks;
//    }
//
//    @Override
//    public List<Book> getBooksByGenre(Genre genre) {
//        List<Book> booksByGenre = new ArrayList<>();
//        for (Book book : bookMap.values()) {
//            if (book.getGenre() != null && book.getGenre().equals(genre)) {
//                booksByGenre.add(book);
//            }
//        }
//        return booksByGenre;
//    }
//
//
////    @Override
////    public List<Book> getBooksByGenre(Genre genre) {
////        List<Book> booksByGenre = new ArrayList<>();
////        for (Book book : bookMap.values()) {
////            System.out.println("Book Genre "+ book.getGenre());
////            if (book.getGenre() != null && book.getGenre().equalsIgnoreCase(genre)) {
////                booksByGenre.add(book);
////            }
////        }
////        return booksByGenre;
////    }
//
//    public List<Book> getBooksByYear(Integer year) {
//        List<Book> booksByYear = new ArrayList<>();
//        for (Book book : bookMap.values()) {
//            if (book.getYear() != null && book.getYear().equals(year)) {
//                booksByYear.add(book);
//            }
//        }
//        return booksByYear;
//    }
//

//
//
//    // Method to delete a book by its ID
//    public void deleteBook(String id) {
//        // Remove the book from the map by its ID
//        bookMap.remove(id);
//    }
//
//    // Method to update a book's information
//    public Book updateBook(String Id, Book book) {
//        // Check if the book with the specified ID exists in the map
//        if (bookMap.containsKey(Id)) {
//            // If the book exists, update its information in the map
//            bookMap.put(Id, book);
//        }
//        // Return the updated book
//        return book;
//    }
//
//
//

//
//
////    public void validateBook(Book book){
////        dummyService.validate(book);
////    }
//
//


}
