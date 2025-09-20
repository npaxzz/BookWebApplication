package com.bookweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookweb.exception.BookNotFoundException;
import com.bookweb.model.Book;
import com.bookweb.repository.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// ดึงหนังสือทั้งหมด
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	// ดึงหนังสือจาก id
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	// ค้นหาหนังสือจาก title
	public List<Book> searchBooksByTitle(String title) {
		List<Book> books = bookRepository.findByTitle(title);
		if (books.isEmpty()) {
			throw BookNotFoundException.forTitle(title);
		}
		return books;
	}

	public List<Book> searchBooksByAuthor(String author) {
		List<Book> books = bookRepository.findByAuthor(author);
		if (books.isEmpty()) {
			throw BookNotFoundException.forAuthor(author);
		}
		return books;
	}

	// บันทึกหนังสือ
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	// ลบหนังสือ
	public void deleteBook(Long id) {
		if (!bookRepository.existsById(id)) {
			new BookNotFoundException(id);
		}
		bookRepository.deleteById(id);
	}
}