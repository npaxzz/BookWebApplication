package com.bookweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookweb.exception.ReviewNotFoundException;
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

	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	public Review getReviewById(Long id) {
		return reviewRepository.findById(id).orElseThrow(() -> ReviewNotFoundException.forId(id));
	}

	public List<Review> getReviewsByBook(Long bookId) {
		return reviewRepository.findByBookId(bookId);
	}

	public Review createReview(Long bookId, Review review) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
		review.setBook(book);
		return reviewRepository.save(review);
	}

	public Review updateReview(Long id, Review reviewDetails) {
		Review review = reviewRepository.findById(id).orElseThrow(() -> ReviewNotFoundException.forId(id));

		review.setReviewerName(reviewDetails.getReviewerName());
		review.setComment(reviewDetails.getComment());
		review.setRating(reviewDetails.getRating());

		if (reviewDetails.getBook() != null) {
			Book book = bookRepository.findById(reviewDetails.getBook().getId()).orElseThrow(
					() -> new RuntimeException("Book not found with id: " + reviewDetails.getBook().getId()));
			review.setBook(book);
		}

		return reviewRepository.save(review);
	}

	public void deleteReview(Long id) {
		if (!reviewRepository.existsById(id)) {
			throw ReviewNotFoundException.forId(id);
		}
		reviewRepository.deleteById(id);
	}
}