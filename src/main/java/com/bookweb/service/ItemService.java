package com.bookweb.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookweb.dto.ItemDTO;
import com.bookweb.dto.ReviewDTO;
import com.bookweb.model.Category;
import com.bookweb.model.Item;
import com.bookweb.repository.CategoryRepository;
import com.bookweb.repository.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;

	public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}

	public ItemDTO convertToDTO(Item item) {
		Set<String> categoryNames = item.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
		Set<ReviewDTO> reviewDTOs = item.getReviews().stream()
				.map(r -> new ReviewDTO(r.getId(), r.getReviewerName(), r.getRating(), r.getComment()))
				.collect(Collectors.toSet());
		String typeName = item.getType() != null ? item.getType().name() : "UNKNOWN";

		return new ItemDTO(item.getId(), item.getTitle(), item.getCreator(), item.getDescription(), typeName,
				categoryNames, reviewDTOs, item.getImageUrl());
	}

	public List<ItemDTO> getAllItems() {
		return itemRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ItemDTO getItemById(Long id) {
		return itemRepository.findById(id).map(this::convertToDTO)
				.orElseThrow(() -> new RuntimeException("Item not found"));
	}

	public ItemDTO createItem(Item item) {
		// map categories
		Set<Category> categories = item.getCategories().stream().map(c -> categoryRepository.findById(c.getId())
				.orElseThrow(() -> new RuntimeException("Category not found"))).collect(Collectors.toSet());
		item.setCategories(categories);
		return convertToDTO(itemRepository.save(item));
	}

	public ItemDTO updateItem(Long id, Item updatedItem) {
		Item existing = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

		existing.setTitle(updatedItem.getTitle());
		existing.setCreator(updatedItem.getCreator());
		existing.setDescription(updatedItem.getDescription());
		existing.setType(updatedItem.getType());
		existing.setImageUrl(updatedItem.getImageUrl());

		Set<Category> categories = updatedItem.getCategories().stream().map(c -> categoryRepository.findById(c.getId())
				.orElseThrow(() -> new RuntimeException("Category not found"))).collect(Collectors.toSet());
		existing.setCategories(categories);

		return convertToDTO(itemRepository.save(existing));
	}

	public void deleteItem(Long id) {
		if (!itemRepository.existsById(id))
			throw new RuntimeException("Item not found");
		itemRepository.deleteById(id);
	}
}
