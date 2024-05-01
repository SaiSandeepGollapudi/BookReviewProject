package com.goodReads.library.service.impl;

import com.goodReads.library.Repositry.BookRepository;
import com.goodReads.library.Repositry.ReviewRepository;
import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;
    @Override
    public void addReview(Review review) {
        Optional<Book> bookOptional= bookRepository.findById(review.getBook().getId());// review is the one given input by user, we check if Book exists with that id
if(bookOptional.isEmpty())
{
    throw new IllegalArgumentException(" Book id doesn't exist");
}
review.setBook(bookOptional.get());// We cannot just write bookOptional as bookOptional type book object can be accessed only by .get
        reviewRepository.save(review);
    }



}
