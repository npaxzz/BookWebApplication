package com.bookweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookweb.dto.ReviewDTO;
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

	public ReviewDTO convertToDTO(Review review) {
		return new ReviewDTO(review.getId(), review.getReviewerName(), review.getRating(), review.getComment());
	}

	public List<ReviewDTO> getAllReviews() {
		return reviewRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ReviewDTO getReviewById(Long id) {
		Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
		return convertToDTO(review);
	}

	public ReviewDTO createReview(Long itemId, Review review) {
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
		review.setItem(item);
		return convertToDTO(reviewRepository.save(review));
	}

	public ReviewDTO updateReview(Long id, Review reviewDetails) {
		Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
		review.setReviewerName(reviewDetails.getReviewerName());
		review.setRating(reviewDetails.getRating());
		review.setComment(reviewDetails.getComment());
		return convertToDTO(reviewRepository.save(review));
	}

	public void deleteReview(Long id) {
		if (!reviewRepository.existsById(id))
			throw new RuntimeException("Review not found");
		reviewRepository.deleteById(id);
	}
}
