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


    public BookResponse(String title, String author, Double rating, Double cost) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.cost = cost;
    }
}
