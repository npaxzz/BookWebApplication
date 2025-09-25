package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GAME")
public class Game extends Item {

	private int price;

	public Game() {
	}

	public Game(String title, String creator, String description, Set<Category> categories, int price) {
		super(title, creator, description, categories);
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
