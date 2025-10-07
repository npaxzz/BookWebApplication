package com.bookweb.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.bookweb.dto.ReviewDTO;
import com.bookweb.model.Item;
import com.bookweb.model.Review;
import com.bookweb.model.User;
import com.bookweb.repository.ItemRepository;
import com.bookweb.service.ReviewService;
import com.bookweb.service.UserService;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    // ดึงรีวิวทั้งหมดของ item
    @GetMapping("/item/{itemId}")
    public List<ReviewDTO> getReviewsByItem(@PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return item.getReviews().stream()
                .map(reviewService::convertToDTO)
                .collect(Collectors.toList());
    }

    // เพิ่มรีวิว (ต้อง login)
    @PostMapping("/item/{itemId}")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable Long itemId,
                                               @RequestParam int rating,
                                               @RequestParam String comment,
                                               HttpSession session) {
        // เช็ค login
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity.status(401).build();
        }

        // เรียก service แบบใหม่
        ReviewDTO savedReview = reviewService.createReview(itemId,
                currentUser.getUsername(),
                rating,
                comment);

        return ResponseEntity.ok(savedReview);
    }

    }

