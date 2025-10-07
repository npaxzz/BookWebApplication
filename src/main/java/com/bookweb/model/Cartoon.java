package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARTOON")
public class Cartoon extends Item {

	public Cartoon() {
		super();
		this.setType(ItemType.CARTOON);
	}

	public Cartoon(String title, String creator, String description, Double price, Set<Category> categories) {
		super(ItemType.CARTOON, title, creator, description, price, categories);
	}
}
