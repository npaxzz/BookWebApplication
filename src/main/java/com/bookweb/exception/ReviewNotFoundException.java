package com.bookweb.exception;

public class ReviewNotFoundException extends RuntimeException {

	public ReviewNotFoundException(Long id) {
		super("Review not found with id: " + id);
	}

	public static ReviewNotFoundException forId(Long id) {
		return new ReviewNotFoundException(id);
	}
}
