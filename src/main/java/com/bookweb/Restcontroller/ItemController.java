package com.bookweb.Restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.dto.ItemDTO;
import com.bookweb.model.Item;
import com.bookweb.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	// http://localhost:8085/items
	@GetMapping
	public List<ItemDTO> getAllItems() {
		return itemService.getAllItems();
	}

	// http://localhost:8085/items/6
	@GetMapping("/{id}")
	public ItemDTO getItem(@PathVariable Long id) {
		return itemService.getItemById(id);
	}

	@PostMapping
	public ItemDTO createItem(@RequestBody Item item) {
		return itemService.createItem(item);
	}

	@PutMapping("/{id}")
	public ItemDTO updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
		return itemService.updateItem(id, updatedItem);
	}

	@DeleteMapping("/{id}")
	public String deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
		return "Item deleted successfully";
	}
}
