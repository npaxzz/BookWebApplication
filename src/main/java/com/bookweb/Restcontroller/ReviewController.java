package com.bookweb.Restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.dto.ReviewDTO;
import com.bookweb.model.Review;
import com.bookweb.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	// http://localhost:8085/api/reviews
	@GetMapping
	public List<ReviewDTO> getAllReviews() {
		return reviewService.getAllReviews();
	}

	// http://localhost:8085/api/reviews/6
	@GetMapping("/{id}")
	public ReviewDTO getReviewById(@PathVariable Long id) {
		return reviewService.getReviewById(id);
	}

	// http://localhost:8085/api/reviews/item/5
	@PostMapping("/item/{itemId}")
	public ReviewDTO createReview(@PathVariable Long itemId, @RequestBody Review review) {
		return reviewService.createReview(itemId, review);
	}

	@PutMapping("/{id}")
	public ReviewDTO updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
		return reviewService.updateReview(id, reviewDetails);
	}

	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return "Review deleted successfully";
	}
}
