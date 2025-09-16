package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookweb.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
