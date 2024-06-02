package com.goodReads.library.service.resource;


import com.goodReads.library.domain.Review;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {// whenever you're giving a response which are of non-connected entities.
    // You should never connect them in your entity class. You will always connect them in your response class like this when
    // you're not using mapping like oneToMany etc

    private String title;
    private String author;

    private Double rating;
    private Double cost;
    List<Review> reviewList;

    //Data Transfer Object (DTO): The UserResponse class is a DTO used to send data to clients.
//Decoupling: Separates the internal entity structure (User) from the response structure (UserResponse).
    //Custom Formatting: Allows for custom formatting or transformations, such as converting id to a string.
    //API Stability: Provides a stable API response format that can remain consistent even if the User entity changes.
    public BookResponse(String title, String author, Double rating, Double cost) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.cost = cost;
    }

//    public BookResponse(Book book) { other way to write above block
//        this.title = title;
//        this.author = author;
//        this.rating = rating;
//        this.cost = cost;
//    }

}
