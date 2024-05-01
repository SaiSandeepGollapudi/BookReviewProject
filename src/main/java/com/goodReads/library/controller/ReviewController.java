package com.goodReads.library.controller;

import com.goodReads.library.service.ReviewService;
import com.goodReads.library.service.resource.ReviewRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/review")//RequestMapping annotation at the class level specifies the base URL path ("/review") for all the request mappings within this controller.
// This means that any request mapping defined within this controller will be relative to the "/review" path.
public class ReviewController {

    @Autowired
    ReviewService reviewService;


@PostMapping("/review")
public ResponseEntity<?> addReview(@RequestBody @Valid ReviewRequest reviewRequest){

    reviewService.addReview(reviewRequest.toReview());
    return new ResponseEntity<>(HttpStatus.CREATED);

}



}
