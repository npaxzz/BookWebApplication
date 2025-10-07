package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookweb.model.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByItemId(Long itemId);
}
