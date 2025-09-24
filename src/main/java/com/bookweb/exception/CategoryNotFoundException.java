package com.bookweb.exception;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(Long id) {
		super("Category with id : " + id + "does not exist");
	}

	private CategoryNotFoundException(String message) {
		super(message);
	}

	public static CategoryNotFoundException forTitle(String category) {
		return new CategoryNotFoundException("Category : " + category + "does not exist");
	}
}