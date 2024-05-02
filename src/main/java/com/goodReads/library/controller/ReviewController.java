package com.goodReads.library.controller;

import com.goodReads.library.domain.Review;
import com.goodReads.library.service.ReviewService;
import com.goodReads.library.service.resource.ReviewRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")//RequestMapping annotation at the class level specifies the base URL path ("/review") for all the request mappings within this controller.
// This means that any request mapping defined within this controller will be relative to the "/review" path.
public class ReviewController {

    @Autowired
    ReviewService reviewService;


    @PostMapping("/{bookId}") // Specify the bookId in the path
    public ResponseEntity<?> addReviewToBook(
            @PathVariable("bookId") Integer bookId, // Extract the bookId from the path
            @RequestBody @Valid ReviewRequest reviewRequest) {

        // Create a Review object from the request
        Review review = reviewRequest.toReview();

        // Call the service method to add the review to the book
        reviewService.addReview(bookId,review);

        // Return a success response
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}




