package com.bookweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookweb.model.Book;
import com.bookweb.model.Review;
import com.bookweb.repository.BookRepository;
import com.bookweb.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    // ดึงรีวิวทั้งหมด
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // ดึงรีวิวตาม ID
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    // เพิ่มรีวิวใหม่
    public Review createReview(Review review) {
        if (review.getBook() != null) {
            Book book = bookRepository.findById(review.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found with id: " + review.getBook().getId()));
            review.setBook(book);
        }
        return reviewRepository.save(review);
    }

    // อัปเดตรีวิว
    public Review updateReview(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        review.setReviewerName(reviewDetails.getReviewerName());
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());

        if (reviewDetails.getBook() != null) {
            Book book = bookRepository.findById(reviewDetails.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found with id: " + reviewDetails.getBook().getId()));
            review.setBook(book);
        }

        return reviewRepository.save(review);
    }

    // ลบรีวิว
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
