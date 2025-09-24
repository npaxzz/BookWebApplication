package com.bookweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookweb.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByTitleContainingIgnoreCase(String title);

	List<Book> findByAuthorContainingIgnoreCase(String author);
}
