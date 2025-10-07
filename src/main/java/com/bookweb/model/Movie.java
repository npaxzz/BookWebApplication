package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Item {

	public Movie() {
		super();
		this.setType(ItemType.MOVIE);
	}

	public Movie(ItemType type, String title, String creator, String description, Double price,
			Set<Category> categories) {
		super(ItemType.MOVIE, title, creator, description, price, categories);
	}
}
