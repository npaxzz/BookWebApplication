package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GAME")
public class Game extends Item {

	public Game() {
		super();
		this.setType(ItemType.GAME);
	}

	public Game(ItemType type, String title, String creator, String description, Double price,
			Set<Category> categories) {
		super(ItemType.GAME, title, creator, description, price, categories);
	}

}
