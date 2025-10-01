package com.bookweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "index"; // templates/index.html
	}

	@GetMapping("/book")
	public String bookPage() {
		return "book"; // templates/book.html
	}

	@GetMapping("/movie")
	public String moviePage() {
		return "movie"; // templates/movie.html
	}

	@GetMapping("/cartoon")
	public String cartoonPage() {
		return "cartoon"; // templates/cartoon.html
	}

	@GetMapping("/game")
	public String gamePage() {
		return "game"; // templates/game.html
	}

	@GetMapping("/category")
	public String categoryPage() {
		return "category"; // templates/category.html
	}
}
