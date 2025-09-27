// CategoryService.java
package com.bookweb.service;

import com.bookweb.model.Category;
import com.bookweb.model.Item;
import com.bookweb.model.ItemType;
import com.bookweb.model.Book;
import com.bookweb.model.Movie;
import com.bookweb.model.Cartoon;
import com.bookweb.model.Game;
import com.bookweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {
    
	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
	    this.categoryRepository = categoryRepository;
	}

	// --- helper ---
	private Category getCategoryOrThrow(Long id) {
	    return categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}

	private void ensureUniqueName(String name, Long excludeId) {
	    if (name == null) return;
	    Category existing = categoryRepository.findByName(name);
	    if (existing != null && (excludeId == null || !existing.getId().equals(excludeId))) {
	        throw new RuntimeException("Category with name '" + name + "' already exists");
	    }
	}

	private <T extends Item> List<T> castItemList(List<Item> items, Class<T> clazz) {
	    return items.stream()
	            .map(clazz::cast)
	            .collect(Collectors.toList());
	}

	public List<Category> getAllCategories() {
	    return categoryRepository.findAll();
	}

	public Category getCategoryById(Long id) {
	    return getCategoryOrThrow(id);
	}

	public Category getCategoryByName(String name) {
	    return categoryRepository.findByName(name);
	}

	public Category saveCategory(Category category) {
	    validateCategory(category);
	    return categoryRepository.save(category);
	}

	public Category createCategory(String name) {
	    return createCategory(name, null);
	}

	public Category createCategory(String name, String description) {
	    if (!isValidCategoryName(name)) {
	        throw new RuntimeException("Invalid category name");
	    }
	    ensureUniqueName(name, null);
	    Category category = new Category(name, description);
	    return saveCategory(category);
	}

	public Category updateCategory(Long id, Category categoryDetails) {
	    Category existingCategory = getCategoryOrThrow(id);

	    String newName = categoryDetails.getName();
	    ensureUniqueName(newName, id);

	    existingCategory.setName(newName);
	    existingCategory.setDescription(categoryDetails.getDescription());
	    if (categoryDetails.getIsActive() != null) {
	        existingCategory.setIsActive(categoryDetails.getIsActive());
	    }

	    return saveCategory(existingCategory);
	}

// 	ถ้ามี item จะลบไม่ได้
	public void deleteCategory(Long id) {
	    deleteCategoryInternal(id, false);
	}

//	ถ้ามี item จะลบไอเทมทิ้งจาก category แล้วค่อยลบ 
	public void forceDeleteCategory(Long id) {
	    deleteCategoryInternal(id, true);
	}

	private void deleteCategoryInternal(Long id, boolean force) {
	    Category category = getCategoryOrThrow(id);

	    if (!force) {
	        if (category.hasItems()) {
	            throw new RuntimeException("Cannot delete category that has " + category.getItemCount() + " items associated with it");
	        }
	        categoryRepository.deleteById(id);
	        return;
	    }

	    Set<Item> items = Set.copyOf(category.getItems());
	    for (Item item : items) {
	        category.removeItem(item);
	    }
	    categoryRepository.deleteById(id);
	}

//	ค้นหา
	public List<Category> searchByName(String keyword) {
	    return categoryRepository.findByNameContaining(keyword);
	}

	public List<Category> searchByDescription(String keyword) {
	    return categoryRepository.findByDescriptionContaining(keyword);
	}

	public List<Category> searchCategories(String keyword) {
	    List<Category> nameResults = searchByName(keyword);
	    List<Category> descriptionResults = searchByDescription(keyword);
	    Set<Category> combined = new LinkedHashSet<>(nameResults);
	    combined.addAll(descriptionResults);
	    return List.copyOf(combined);
	}

//	เพิ่ม ลด ของ
	public void addItemToCategory(Long categoryId, Item item) {
	    Category category = getCategoryOrThrow(categoryId);
	    category.addItem(item);
	    saveCategory(category);
	}

	public void removeItemFromCategory(Long categoryId, Item item) {
	    Category category = getCategoryOrThrow(categoryId);
	    category.removeItem(item);
	    saveCategory(category);
	}

	public List<Item> getItemsByCategory(Long categoryId) {
	    Category category = getCategoryOrThrow(categoryId);
	    return List.copyOf(category.getItems());
	}

	public List<Item> getItemsByTypeAndCategory(Long categoryId, ItemType itemType) {
	    Category category = getCategoryOrThrow(categoryId);
	    return List.copyOf(category.getItemsByType(itemType));
	}

	public List<Book> getBooksByCategory(Long categoryId) {
	    return castItemList(getItemsByTypeAndCategory(categoryId, ItemType.BOOK), Book.class);
	}

	public List<Movie> getMoviesByCategory(Long categoryId) {
	    return castItemList(getItemsByTypeAndCategory(categoryId, ItemType.MOVIE), Movie.class);
	}

	public List<Cartoon> getCartoonsByCategory(Long categoryId) {
	    return castItemList(getItemsByTypeAndCategory(categoryId, ItemType.CARTOON), Cartoon.class);
	}

	public List<Game> getGamesByCategory(Long categoryId) {
	    return castItemList(getItemsByTypeAndCategory(categoryId, ItemType.GAME), Game.class);
	}

	public List<Category> getCategoriesWithItems() {
	    return getAllCategories().stream()
	            .filter(Category::hasItems)
	            .collect(Collectors.toList());
	}

	public List<Category> getCategoriesWithoutItems() {
	    return getAllCategories().stream()
	            .filter(category -> !category.hasItems())
	            .collect(Collectors.toList());
	}

	public List<Category> getCategoriesWithItemType(ItemType itemType) {
	    return getAllCategories().stream()
	            .filter(category -> category.hasItemType(itemType))
	            .collect(Collectors.toList());
	}

	public List<Category> getCategoriesOrderByItemCount() {
	    return getAllCategories().stream()
	            .sorted((c1, c2) -> Integer.compare(c2.getItemCount(), c1.getItemCount()))
	            .collect(Collectors.toList());
	}

	public List<Category> getCategoriesWithMoreThanItems(int itemCount) {
	    return getAllCategories().stream()
	            .filter(category -> category.getItemCount() > itemCount)
	            .collect(Collectors.toList());
	}
	
    public boolean isValidCategoryName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 255;
    }
    
    public boolean isValidCategoryDescription(String description) {
        return description == null || description.length() <= 1000;
    }
    
    public void validateCategory(Category category) {
        if (!isValidCategoryName(category.getName())) {
            throw new RuntimeException("Invalid category name");
        }
        if (!isValidCategoryDescription(category.getDescription())) {
            throw new RuntimeException("Invalid category description");
        }
    }
}