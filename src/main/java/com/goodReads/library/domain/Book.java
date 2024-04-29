package com.goodReads.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Table(name="books")
@Entity
@Builder
public class Book {

// I should never allow my controller or BookRequest to play with my domain, i'm separating it out with for the safety concerns and better practices, it should only be
    // used for  handling the db and use another resource like BookRequest for getting data from user
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)//IDENTITY -> which is supported by the under-laying database. eg: mysql, sql server.
private Integer id;
    private String title;
    private String author;
//    private String genre;
@Enumerated(value = EnumType.STRING)// as db doesn't have an enum type, by default spring converts into int with its indices so we can change it with value = EnumType.STRING
private Genre genre;
    private Double rating; //Non-primitive wrapper classes allow for null values to be assigned to these fields.
    // For example, if a book's rating or cost is not available or not applicable,
    // You can set them to null. A primitive type always has a value. Can be used in collections like List, Map, etc.,
    // without the need for autoboxing/unboxing. Integer provides methods for parsing strings to integers (parseInt())
    // and converting integers to strings (toString())
    private Double cost;

   // private List<Review> reviewList;

    private Integer year;

    // Book - Review
    // One book can have multiple reviews
    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER,cascade= CascadeType.DETACH)///@OneToMany- As it's in the Book class and books can have many reviews.
    // Mapped by is equal to which field in Review owns this relationship.It's the book field.// As per JPA,If the relationship is bidirectional, the mappedBy element must be used
    // to specify the relationship field or property of the entity that is the owner of the relationship.
    @JsonIgnoreProperties("book")// we are telling book object should be ignored when retrieving Review so that it doesn't go into loop
    private List<Review> reviewList;
    public List<Review> getReviewList() {
        return reviewList;
    }
    public Book() {

    }

    /***
     * Types of ID generation
     *
     *     TABLE -> keeping a separate table for the Id generation.
     *     SEQUENCE -> where the last sequence is identified and IDs are updated . -> postgres
     *     IDENTITY -> which is supported by the under-laying database. eg: mysql, sql server.
     *     UUID -> generate a unique identifer, randomly generated.
     *     AUTO -> which will the framwork to support the one by the under-laying data or go forward with Identity.
     * */
    @Override
    public int hashCode() {
        return Integer.valueOf(getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

//    public List<Review> getReviewList() {
//        return reviewList;
//    }

//    public void setReviewList(List<Review> reviewList) {
//        this.reviewList = reviewList;
//    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

//    public Book() {
//        this.reviewList = new ArrayList<>();
//
//    }

    public Book(Integer id, String title, String author, Genre genre, Double rating, Double cost, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.cost = cost;
        //this.reviewList = reviewList;
        this.year = year;
    }


}


