package com.goodReads.library.service.resource;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Genre;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    //This class is created to so that data inserted by user is take care here
    @NotBlank(message = "title cannot be blank")
    private String title;
    @NotBlank(message = "author cannot be blank")
    private String author;
    private Genre genre;
    @Min(value = 0, message = "cost cannot be less than zero")
    private Double cost;
    @Min(value = 1000, message = "year cannot be less than 1000")
    private Integer year;
    public Book getBook() {
        // Creates and returns a new Book object with the same attributes as the current book object
        //This method is created to so that data inserted by user is take care here

        return Book.builder() // So we converted our BookRequest to Book // Starts building a new Book object using a builder it's better alternative to constructor and setters
                .title(this.title) // Sets the title of the new book to the same as the current book's title
                .author(this.author) // Sets the author of the new book to the same as the current book's author
                .rating(0.0) // Sets the rating of the new book to 0.0 (possibly a default value)
                .genre(this.genre) // Sets the genre of the new book to the same as the current book's genre
                .cost(this.cost) // Sets the cost of the new book to the same as the current book's cost
                .year(this.year) // Sets the year of the new book to the same as the current book's year
                .build(); // Builds and returns the new Book object
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
