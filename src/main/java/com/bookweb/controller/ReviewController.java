package com.bookweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.model.Review;
import com.bookweb.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	// ดึงรีวิวทั้งหมด
	@GetMapping
	public List<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}

	// ดึงรีวิวตาม id
	@GetMapping("/{id}")
	public Review getReviewById(@PathVariable Long id) {
		return reviewService.getReviewById(id);
	}

	// ดึงรีวิวของ Book
	@GetMapping("/book/{bookId}")
	public List<Review> getReviewsByBook(@PathVariable Long bookId) {
		return reviewService.getReviewsByBook(bookId);
	}

	// เพิ่มรีวิวให้ Book
	@PostMapping("/book/{bookId}")
	public Review addReview(@PathVariable Long bookId, @RequestBody Review review) {
		return reviewService.createReview(bookId, review);
	}

	// อัปเดตรีวิว
	@PutMapping("/{id}")
	public Review updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
		return reviewService.updateReview(id, reviewDetails);
	}

	// ลบรีวิว
	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return "Review deleted successfully";
	}
}