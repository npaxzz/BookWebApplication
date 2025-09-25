package com.bookweb.model;

import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARTOON")
public class Cartoon extends Item {

	private String studio;

	public Cartoon() {
		super();
		this.setType(ItemType.CARTOON);
	}

	public Cartoon(String title, String creator, String description, Set<Category> categories, String studio) {
		super(ItemType.CARTOON, title, creator, description, categories);
		this.studio = studio;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}
}
