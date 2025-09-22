package com.bookweb.controller;

public class ReviewController {

}
#อันใหม่
package com.bookweb.controller;

import com.bookweb.model.Review;
import com.bookweb.model.Book;
import com.bookweb.repository.ReviewRepository;
import com.bookweb.repository.BookRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewController(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    // ดึงรีวิวทั้งหมด
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewRepository.findAll(), HttpStatus.OK);
    }

    // ดึงรีวิวตาม id
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> new ResponseEntity<>(review, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // เพิ่มรีวิวใหม่
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        if (review.getBook() != null) {
            Book book = bookRepository.findById(review.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            review.setBook(book);
        }
        Review savedReview = reviewRepository.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // อัปเดตรีวิว
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setReviewerName(reviewDetails.getReviewerName());
                    review.setRating(reviewDetails.getRating());
                    review.setComment(reviewDetails.getComment());
                    if (reviewDetails.getBook() != null) {
                        Book book = bookRepository.findById(reviewDetails.getBook().getId())
                                .orElseThrow(() -> new RuntimeException("Book not found"));
                        review.setBook(book);
                    }
                    return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ลบรีวิว
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        if (!reviewRepository.existsById(id)) {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
        reviewRepository.deleteById(id);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
