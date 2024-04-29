package com.goodReads.library.service.resource;

import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

        @NotBlank(message = "Comment cannot be empty")
        private String comment;
        @Min(value=0, message = " value should be greater than or equal to 0")
        @Max(value=5, message="rating cannot be greater than 5")
        private Double rating;

        @Min(value=0, message="bookId should be greater than 0")
        private Integer bookId;


        public Review toReview(){
            return Review.builder().comment(this.comment).rating(this.rating).book(Book.builder().id(this.bookId).build()).build();
        }
}
