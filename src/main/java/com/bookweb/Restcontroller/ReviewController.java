package com.bookweb.Restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.dto.ReviewDTO;
import com.bookweb.model.Item;
import com.bookweb.model.User;
import com.bookweb.repository.ItemRepository;
import com.bookweb.service.ReviewService;
import com.bookweb.service.UserService;

import jakarta.servlet.http.HttpSession;

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
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
		return item.getReviews().stream().map(reviewService::convertToDTO).collect(Collectors.toList());
	}

	// เพิ่มรีวิว (ต้อง login)
	@PostMapping("/item/{itemId}")
	public ResponseEntity<ReviewDTO> addReview(@PathVariable Long itemId, @RequestParam int rating,
			@RequestParam String comment, HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		if (currentUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		ReviewDTO savedReview = reviewService.createReview(itemId, currentUser.getUsername(), rating, comment);
		return ResponseEntity.ok(savedReview);
	}

}