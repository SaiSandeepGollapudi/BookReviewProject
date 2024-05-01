package com.goodReads.library.Repositry;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByYear(Integer year);
    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthorAndYear(String author, Integer Year);

    //    List<Book>  findBooksByRating(Double rating);
    List<Book> findByTitleIgnoreCaseContaining(String title);

    boolean existsByAuthor(String author);

    boolean existsByTitle(String title);

    boolean existsByYear(Integer year);

    @Query("SELECT book FROM Book book WHERE book.author = :author AND book.genre = :genre")
    List<Book> findByAuthorAndGenre(String author, Genre genre);

    @Query("select book from Book book where title like :title")// @Query to write custom queries, * is not supported, so we use book from Book book
    List<Book> findByTitleLike2(String title);
@Query("select book from Book book  where title like ?1")// what ever is passed in the String parameter for title is replaced by 1
    List<Book> findByTitleLike(String title);

    boolean existsByAuthorAndYear(String author, Integer year);

    boolean existsByGenre(Genre genre);

/**
 * Sample pageable request
 * // Create a Pageable object for the first page with 10 items per page, sorted by rating in descending order
 * Pageable pageable = PageRequest.of(0, 10, Sort.by("rating").descending());
 *     Page<Book> findByRating(Double rating, Pageable pageable);
 * */

//@Query(value="select * from books where rating > ?",nativeQuery = true)
//List<Book> giveMeTopBooks(Double rating);

//List<Book> findByRatingGreaterThan(Double rating);

// Method to find average rating of books grouped by author

//@Query("SELECT b.author, AVG(b.rating) FROM Book b GROUP BY b.author")
//List<Book> findAverageRatingByAuthorWithCustomQuery();
//    // without JPQL for average
//    // Method to find average rating of books grouped by author
//    List<Book> findAverageRatingByAuthor(String name);


/****
 * JPQL
 * - create a custom query for the search.
 * Done by @Query annotation and values are mapped by ?1 or ?2 etc. or: variableName
 * - * is not supported as JPQL because, Java cannot understand *.
 *
 * */
}
