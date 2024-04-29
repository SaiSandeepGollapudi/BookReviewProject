package com.goodReads.library.Repositry;

import com.goodReads.library.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
}
