package com.bookweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookweb.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByBookId(Long bookId);
}
