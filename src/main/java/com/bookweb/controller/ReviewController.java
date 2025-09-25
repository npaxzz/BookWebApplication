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

	@GetMapping
	public List<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}

	@GetMapping("/{id}")
	public Review getReviewById(@PathVariable Long id) {
		return reviewService.getReviewById(id);
	}

	@GetMapping("/item/{itemId}")
	public List<Review> getReviewsByItem(@PathVariable Long itemId) {
		return reviewService.getReviewsByItem(itemId);
	}

	@PostMapping("/item/{itemId}")
	public Review addReview(@PathVariable Long itemId, @RequestBody Review review) {
		return reviewService.createReview(itemId, review);
	}

	@PutMapping("/{id}")
	public Review updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
		return reviewService.updateReview(id, reviewDetails);
	}

	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return "Review deleted successfully";
	}
}