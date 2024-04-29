package com.goodReads.library.controller;

import com.goodReads.library.Repositry.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;
}
