package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Item {

	public Book() {
		super();
		this.setType(ItemType.BOOK);
	}

	public Book(String title, String creator, String description, Double price, Set<Category> categories) {
		super(ItemType.BOOK, title, creator, description, price, categories);
	}
}
