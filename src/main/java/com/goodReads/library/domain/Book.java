package com.goodReads.library.domain;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Book {


    private String id;
    private String title;
    private String author;
//    private String genre;
private Genre genre;
    private Double rating; //non-primitive wrapper classes allows for null values to be assigned to these fields. For example, if a book's rating or cost is not available or not applicable,
    // you can set them to null. A primitive type always has a value. Can be used in collections like List, Map, etc.,
    // without the need for autoboxing/unboxing. Integer provides methods for parsing strings to integers (parseInt())
    // and converting integers to strings (toString())
    private Double cost;

    private List<Review> reviewList;

    private Integer year;

    @Override
    public int hashCode() {
        return Integer.valueOf(getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Book() {
        this.reviewList = new ArrayList<>();

    }

    public Book(String id, String title, String author, Genre genre, Double rating, Double cost, List<Review> reviewList, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.cost = cost;
        this.reviewList = reviewList;
        this.year = year;
    }


}


