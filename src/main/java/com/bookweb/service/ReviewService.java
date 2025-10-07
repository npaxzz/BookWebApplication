package com.bookweb.service;

import org.springframework.stereotype.Service;
import com.bookweb.model.Review;
import com.bookweb.model.Item;
import com.bookweb.repository.ReviewRepository;
import com.bookweb.repository.ItemRepository;
import com.bookweb.dto.ReviewDTO;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;

    public ReviewService(ReviewRepository reviewRepository, ItemRepository itemRepository) {
        this.reviewRepository = reviewRepository;
        this.itemRepository = itemRepository;
    }

    public ReviewDTO convertToDTO(Review review) {
        return new ReviewDTO(review.getId(),
                             review.getReviewerName(),
                             review.getRating(),
                             review.getComment());
    }

    // createReview แบบใช้ reviewerName
    public ReviewDTO createReview(Long itemId, String reviewerName, int rating, String comment) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Review review = new Review();
        review.setReviewerName(reviewerName);
        review.setRating(rating);
        review.setComment(comment);
        review.setItem(item); // <-- แก้ตรงนี้

        Review saved = reviewRepository.save(review);
        return convertToDTO(saved);
    }

}

