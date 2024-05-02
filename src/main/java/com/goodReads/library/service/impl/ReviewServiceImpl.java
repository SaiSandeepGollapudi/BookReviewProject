package com.goodReads.library.service.impl;

import com.goodReads.library.Repositry.BookRepository;
import com.goodReads.library.Repositry.ReviewRepository;
import com.goodReads.library.domain.Book;
import com.goodReads.library.domain.Review;
import com.goodReads.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//public class ReviewServiceImpl implements ReviewService {
//    @Autowired
//    ReviewRepository reviewRepository;
//
//    @Autowired
//    BookRepository bookRepository;
//
//    @Override
//    public void addReview(Integer bookId,Review review) {
//        // Retrieve the book by its ID
//        Optional<Book> bookOptional = bookRepository.findById(review.getBook().getId());
//        if (!bookOptional.isEmpty()) {
//            bookOptional.get().getReviewList().add(review);
//
//        }
//
//        Book book = bookOptional.get();
//
//        // Save the review
//        review.setBook(book);
//        reviewRepository.save(review);
//
//        Optional<Book> bookOptional1= bookRepository.findById(bookId);
//        List<Review> reviewList= bookOptional1.get().getReviewList();
//        List<Double> ratings= new ArrayList<Double>();
//        for(Review review1: reviewList){
//
//            ratings.add(review1.getRating());
//        }
//        Double ratingSum=0.0;
//        for(Double rating2: ratings){
//            ratingSum += rating2;
//        }
//        Double avgRating=ratingSum/ratings.size();
//        bookOptional1.get().setRating(avgRating);
//    }
//    }

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void addReview(Integer bookId, Review review) {
        // Retrieve the book by its ID
        Optional<Book> bookOptional = bookRepository.findById(bookId);// The result is wrapped in an Optional to handle cases where the book may not exist.
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            // Add the review to the book's review list
            book.getReviewList().add(review);

            // Update the book's rating
            updateBookRating(book);//is called to recalculate and update the book's rating based on its reviews.

            // Save the review
            review.setBook(book);//the review is associated with the book
            reviewRepository.save(review);//  saved to the database using reviewRepository.save(review).
        } else {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found");
        }
    }

    private void updateBookRating(Book book) {
        List<Review> reviewList = book.getReviewList();
        if (!reviewList.isEmpty()) {
            double totalRating = 0.0;
            for (Review review : reviewList) {//it calculates the total rating by summing up the ratings of all the reviews.
                totalRating += review.getRating();
            }
            double averageRating = totalRating / reviewList.size();// it calculates the average rating by dividing the total rating by the number of reviews.
            book.setRating(averageRating);//The average rating is set to the book's rating attribute
            bookRepository.save(book);//the updated book object is saved to the database
        }
    }

}
///Book book1= bookMap.get(bookId);
//        List<Review> reviewList= book1.getReviewList();
//        List<Double> ratings= new ArrayList<Double>();
//        for(Review review1: reviewList){
//
//             ratings.add(review1.getRating());
//        }
//        Double ratingSum=0.0;
//        for(Double rating2: ratings){
//            ratingSum += rating2;
//        }
//        Double avgRating=ratingSum/ratings.size();
//        book1.setRating(avgRating);
//    }

// Method to update the book's rating based on its reviews
//    private void updateBookRating(Book book) {
//        List<Review> reviewList = book.getReviewList();
//        double totalRating = 0.0;
//
//        // Calculate the total rating
//        for (Review review : reviewList) {
//            totalRating += review.getRating();
//        }
//
//        // Calculate the average rating
//        double averageRating = 0.0;
//        if (!reviewList.isEmpty()) {
//            averageRating = totalRating / reviewList.size();
//        }
//
//        // Update the book's rating
//        book.setRating(averageRating);
//        bookRepository.save(book);
//    }

//    @Override
//    public void addReview(Review review) {
//        // Retrieve the book by its ID
//        Optional<Book> bookOptional = bookRepository.findById(review.getBook().getId());
//        if (bookOptional.isEmpty()) {
//            throw new IllegalArgumentException("Book id doesn't exist");
//        }
//
//        Book book = bookOptional.get();
//
//        // Save the review
//        review.setBook(book);
//        reviewRepository.save(review);
//
//
//    }







