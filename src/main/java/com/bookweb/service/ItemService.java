package com.bookweb.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookweb.dto.CategoryDTO;
import com.bookweb.dto.ItemDTO;
import com.bookweb.dto.ReviewDTO;
import com.bookweb.model.Category;
import com.bookweb.model.Item;
import com.bookweb.model.ItemType;
import com.bookweb.model.User;
import com.bookweb.repository.CategoryRepository;
import com.bookweb.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {

	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;
	private static final String UPLOAD_DIR = "uploads/"; // โฟลเดอร์ relative path ใน project

	public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}

	public ItemDTO convertToDTO(Item item) {
		Set<String> categoryNames = item.getCategories().stream().map(Category::getName).collect(Collectors.toSet());

		Set<ReviewDTO> reviewDTOs = item
				.getReviews().stream().map(r -> new ReviewDTO(r.getId(),
						r.getReviewerName() != null ? r.getReviewerName() : "Anonymous", r.getRating(), r.getComment()))
				.collect(Collectors.toSet());

		String typeName = item.getType() != null ? item.getType().name() : "UNKNOWN";
		String imageUrl = item.getImageUrl() != null ? item.getImageUrl() : "/img/placeholder.png";

		Long ownerId = item.getOwner() != null ? item.getOwner().getId() : null;

		return new ItemDTO(item.getId(), item.getTitle(), item.getCreator(), item.getDescription(), typeName,
				categoryNames, reviewDTOs, imageUrl, item.getPrice(), ownerId);
	}

	public List<ItemDTO> getAllItems() {
		return itemRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ItemDTO getItemById(Long id) {
		return itemRepository.findById(id).map(this::convertToDTO)
				.orElseThrow(() -> new RuntimeException("Item not found"));
	}

	public ItemDTO createItem(Item item) {
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

	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll().stream().map(c -> new CategoryDTO(c.getId(), c.getName()))
				.collect(Collectors.toList());
	}

	public List<ItemDTO> getItemsByCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		return category.getItems().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// สร้าง method saveImage
	public String saveImage(MultipartFile imageFile) throws IOException {
		if (imageFile == null || imageFile.isEmpty()) {
			return null;
		}

		// สร้างโฟลเดอร์ถ้ายังไม่มี
		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// ตั้งชื่อไฟล์แบบไม่ซ้ำ (timestamp + original filename)
		String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
		Path filepath = Paths.get(UPLOAD_DIR, filename);

		// บันทึกไฟล์ลง disk
		Files.write(filepath, imageFile.getBytes());

		// return path ที่ใช้ใน HTML / DB
		return "/" + UPLOAD_DIR + filename;
	}

	public ItemDTO addItem(ItemDTO itemDTO, MultipartFile imageFile, User owner) throws IOException {
		Item item = new Item();
		item.setTitle(itemDTO.getTitle());
		item.setCreator(itemDTO.getCreator());
		item.setDescription(itemDTO.getDescription());
		item.setType(ItemType.valueOf(itemDTO.getType()));
		item.setPrice(itemDTO.getPrice());
		item.setOwner(owner);

		// categories
		Set<Category> categories = itemDTO.getCategoryId().stream().map(
				id -> categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found")))
				.collect(Collectors.toSet());
		item.setCategories(categories);

		// บันทึกรูป
		if (imageFile != null && !imageFile.isEmpty()) {
			String imagePath = saveImage(imageFile);
			item.setImageUrl(imagePath);
		} else {
			item.setImageUrl(itemDTO.getImageUrl());
		}

		return convertToDTO(itemRepository.save(item));
	}
}
