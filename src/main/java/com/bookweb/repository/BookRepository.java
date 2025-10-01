package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookweb.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
