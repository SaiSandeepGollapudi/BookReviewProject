package com.goodReads.library.service;

import com.goodReads.library.domain.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    void addReview(Integer bookId,Review review);


}
