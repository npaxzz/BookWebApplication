package com.bookweb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookweb.dto.ItemDTO;
import com.bookweb.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemWebController {

	private final ItemService itemService;

	public ItemWebController(ItemService itemService) {
		this.itemService = itemService;
	}

	// แสดงรายการทั้งหมด (รวมทุกประเภท)
	@GetMapping
	public String allItems(Model model) {
		List<ItemDTO> items = itemService.getAllItems();
		model.addAttribute("items", items);
		return "items"; // templates/items.html
	}

	// แสดงรายการตามประเภท เช่น /items/books
	@GetMapping("/books")
	public String allBooks(Model model) {
		List<ItemDTO> books = itemService.getAllItems().stream().filter(i -> "BOOK".equals(i.getType()))
				.collect(Collectors.toList());
		model.addAttribute("items", books);
		return "books"; // templates/books.html
	}

	@GetMapping("/movies")
	public String allMovies(Model model) {
		List<ItemDTO> movies = itemService.getAllItems().stream().filter(i -> "MOVIE".equals(i.getType()))
				.collect(Collectors.toList());
		model.addAttribute("items", movies);
		return "movies"; // templates/movies.html
	}

	@GetMapping("/cartoons")
	public String allCartoons(Model model) {
		List<ItemDTO> cartoons = itemService.getAllItems().stream().filter(i -> "CARTOON".equals(i.getType()))
				.collect(Collectors.toList());
		model.addAttribute("items", cartoons);
		return "cartoons"; // templates/cartoons.html
	}

	@GetMapping("/games")
	public String allGames(Model model) {
		List<ItemDTO> games = itemService.getAllItems().stream().filter(i -> "GAME".equals(i.getType()))
				.collect(Collectors.toList());
		model.addAttribute("items", games);
		return "games"; // templates/games.html
	}

	// แสดงรายละเอียดของ item ตาม id
	@GetMapping("/{id}")
	public String itemDetail(@PathVariable Long id, Model model) {
		ItemDTO item = itemService.getItemById(id);
		model.addAttribute("item", item);
		return "itemDetail"; // templates/itemDetail.html
	}
}
