package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookweb.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
