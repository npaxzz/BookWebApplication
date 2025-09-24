package com.bookweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.model.Book;
import com.bookweb.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// GET: ดึงหนังสือทั้งหมด
	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	// GET: ดึงหนังสือตาม id
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}

	// GET: ค้นหาหนังสือจาก title , author
	// http://localhost:8085/books/search?title=...
	// http://localhost:8085/books/search?author=...
	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam(required = false) String title,
			@RequestParam(required = false) String author) {
		if (title != null && !title.isEmpty()) {
			return bookService.searchBooksByTitle(title);
		} else if (author != null && !author.isEmpty()) {
			return bookService.searchBooksByAuthor(author);
		} else {
			return bookService.getAllBooks();
		}
	}

	// POST: เพิ่มหนังสือใหม่
	// title , author , description , categories : []
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	// Put: อัพเดตหนังสือ
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
		return bookService.updateBook(id, updatedBook);
	}

	// DELETE: ลบหนังสือ
	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return "Book deleted successfully";
	}
}
