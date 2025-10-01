package com.bookweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookweb.model.Book;
import com.bookweb.repository.BookRepository;



@Controller
public class BookController {
	
	

	    @Autowired
	    private BookRepository bookRepository;

	    // แสดงรายการหนังสือทั้งหมด
	    @GetMapping("/books")
	    public String getAllBooks(Model model) {        
	        List<Book> books = bookRepository.findAll();
	        model.addAttribute("books", books);
	        return "books"; // ให้ไปที่ไฟล์ books.html
	    }

	    // แสดงรายละเอียดหนังสือ
	    @GetMapping("/books/{id}")
	    public String getBookById(@PathVariable Long id, Model model) {
	        Book book = bookRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + id));
	        
	        model.addAttribute("book", book);
	        return "book-detail"; // ให้ไปที่ไฟล์ book-detail.html
	    }
	}
