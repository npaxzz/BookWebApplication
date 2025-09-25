package com.bookweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookweb.model.Cartoon;
import com.bookweb.model.Game;
import com.bookweb.model.Item;
import com.bookweb.model.ItemType;
import com.bookweb.repository.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	public Item getItemById(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
	}

	public List<Item> searchItems(String title, String creator, ItemType type) {
		if (title != null && !title.isEmpty()) {
			return itemRepository.findByTitleContainingIgnoreCase(title);
		} else if (creator != null && !creator.isEmpty()) {
			return itemRepository.findByCreatorContainingIgnoreCase(creator);
		} else if (type != null) {
			return itemRepository.findByType(type);
		} else {
			return itemRepository.findAll();
		}
	}

	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	public Item updateItem(Long id, Item updatedItem) {
		Item existing = getItemById(id);
		existing.setTitle(updatedItem.getTitle());
		existing.setCreator(updatedItem.getCreator());
		existing.setDescription(updatedItem.getDescription());
		existing.setCategories(updatedItem.getCategories());

		// เฉพาะ subclass: Cartoon, Game
		if (existing instanceof Cartoon && updatedItem instanceof Cartoon) {
			((Cartoon) existing).setStudio(((Cartoon) updatedItem).getStudio());
		} else if (existing instanceof Game && updatedItem instanceof Game) {
			((Game) existing).setPrice(((Game) updatedItem).getPrice());
		}

		return itemRepository.save(existing);
	}

	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}
}
