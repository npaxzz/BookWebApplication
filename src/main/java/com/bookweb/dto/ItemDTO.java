package com.bookweb.dto;

import java.util.Set;

public class ItemDTO {
	private Long id;
	private String title;
	private String creator;
	private String description;
	private String type; // BOOK, MOVIE, CARTOON, GAME
	private Set<String> categories;
	private Set<ReviewDTO> reviews;
	private String imageUrl = "/img/placeholder.png";

	public ItemDTO() {
	}

	public ItemDTO(Long id, String title, String creator, String description, String type, Set<String> categories,
			Set<ReviewDTO> reviews, String imageUrl) {
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.type = type;
		this.categories = categories;
		this.reviews = reviews;
		this.imageUrl = imageUrl;
	}

	// ----- Getter/Setter -----
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Set<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewDTO> reviews) {
		this.reviews = reviews;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
