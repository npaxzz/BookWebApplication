package com.bookweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookweb.model.Item;
import com.bookweb.model.Review;
import com.bookweb.repository.ItemRepository;
import com.bookweb.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ItemRepository itemRepository;

	public ReviewService(ReviewRepository reviewRepository, ItemRepository itemRepository) {
		this.reviewRepository = reviewRepository;
		this.itemRepository = itemRepository;
	}

	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	public Review getReviewById(Long id) {
		return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
	}

	public List<Review> getReviewsByItem(Long itemId) {
		return reviewRepository.findByItemId(itemId);
	}

	public Review createReview(Long itemId, Review review) {
		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
		review.setItem(item);
		return reviewRepository.save(review);
	}

	public Review updateReview(Long id, Review reviewDetails) {
		Review review = getReviewById(id);
		review.setReviewerName(reviewDetails.getReviewerName());
		review.setRating(reviewDetails.getRating());
		review.setComment(reviewDetails.getComment());
		return reviewRepository.save(review);
	}

	public void deleteReview(Long id) {
		if (!reviewRepository.existsById(id)) {
			throw new RuntimeException("Review not found with id: " + id);
		}
		reviewRepository.deleteById(id);
	}
}
