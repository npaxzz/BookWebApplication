package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GAME")
public class Game extends Item {

	private Double price;

	public Game() {
		super();
		this.setType(ItemType.GAME);
	}

	public Game(ItemType type, String title, String creator, String description, Set<Category> categories,
			Double price) {
		super(ItemType.GAME, title, creator, description, categories);
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
