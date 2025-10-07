package com.bookweb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookweb.dto.ItemDTO;
import com.bookweb.model.Category;
import com.bookweb.repository.CategoryRepository;
import com.bookweb.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemWebController {

	private final ItemService itemService;
	private final CategoryRepository categoryRepository;

	public ItemWebController(ItemService itemService, CategoryRepository categoryRepository) {
		this.itemService = itemService;
		this.categoryRepository = categoryRepository;
	}

	private List<ItemDTO> safeList(List<ItemDTO> list) {
		return list != null ? list : new ArrayList<>();
	}

	@GetMapping("/book")
	public String allBooks(Model model) {
		List<ItemDTO> books = safeList(itemService.getAllItems().stream()
				.filter(i -> "BOOK".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", books);
		return "book"; // templates/book.html
	}

	@GetMapping("/movie")
	public String allMovies(Model model) {
		List<ItemDTO> movies = safeList(itemService.getAllItems().stream()
				.filter(i -> "MOVIE".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", movies);
		return "movie"; // templates/movie.html
	}

	@GetMapping("/cartoon")
	public String allCartoons(Model model) {
		List<ItemDTO> cartoons = safeList(itemService.getAllItems().stream()
				.filter(i -> "CARTOON".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", cartoons);
		return "cartoon"; // templates/cartoon.html
	}

	@GetMapping("/game")
	public String allGames(Model model) {
		List<ItemDTO> games = safeList(itemService.getAllItems().stream()
				.filter(i -> "GAME".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", games);
		return "game"; // templates/game.html
	}

	@GetMapping("/category")
	public String allCategories(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "category";
	}

	@GetMapping("/{id}")
	public String itemDetail(@PathVariable Long id, Model model, Principal principal) {
	    ItemDTO item = itemService.getItemById(id);
	    model.addAttribute("item", item);

	    String username = principal != null ? principal.getName() : null;
	    model.addAttribute("username", username);

	    return "itemDetail";
	}


}


