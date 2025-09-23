package com.bookweb.exception;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(Long id) {
		super("Book not found with id: " + id);
	}

	private BookNotFoundException(String message) {
		super(message);
	}

	public static BookNotFoundException forTitle(String title) {
		return new BookNotFoundException("Book not found with title: " + title);
	}

	public static BookNotFoundException forAuthor(String author) {
		return new BookNotFoundException("Book not found with author: " + author);
	}
}
