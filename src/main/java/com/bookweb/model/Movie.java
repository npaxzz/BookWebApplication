package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Item {

	public Movie() {
	}

	public Movie(String title, String creator, String description, Set<Category> categories) {
		super(title, creator, description, categories);
	}
}
