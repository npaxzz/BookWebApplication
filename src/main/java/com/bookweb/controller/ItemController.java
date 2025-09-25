package com.bookweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.model.Item;
import com.bookweb.model.ItemType;
import com.bookweb.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@GetMapping("/{id}")
	public Item getItemById(@PathVariable Long id) {
		return itemService.getItemById(id);
	}

	@GetMapping("/search")
	public List<Item> searchItems(@RequestParam(required = false) String title,
			@RequestParam(required = false) String creator, @RequestParam(required = false) ItemType type) {
		return itemService.searchItems(title, creator, type);
	}

	@PostMapping
	public Item addItem(@RequestBody Item item) {
		return itemService.saveItem(item);
	}

	@PutMapping("/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
		return itemService.updateItem(id, updatedItem);
	}

	@DeleteMapping("/{id}")
	public String deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
		return "Item deleted successfully";
	}
}
