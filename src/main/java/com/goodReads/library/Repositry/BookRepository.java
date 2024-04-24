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


//List<Book> findByRatingGreaterThan(Double rating);

/****
 * JPQL
 * - create a custom query for the search.
 * Done by @Query annotation and values are mapped by ?1 or ?2 etc. or: variableName
 * - * is not supported as JPQL because, Java cannot understand *.
 *
 * */
}
