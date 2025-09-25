package com.bookweb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookweb.dto.CategoryDTO;
import com.bookweb.model.Category;
import com.bookweb.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryDTO convertToDTO(Category category) {
		return new CategoryDTO(category.getId(), category.getName());
	}

	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public CategoryDTO getCategoryById(Long id) {
		return categoryRepository.findById(id).map(this::convertToDTO)
				.orElseThrow(() -> new RuntimeException("Category not found"));
	}

	public CategoryDTO createCategory(Category category) {
		return convertToDTO(categoryRepository.save(category));
	}

	public CategoryDTO updateCategory(Long id, Category updatedCategory) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		category.setName(updatedCategory.getName());
		return convertToDTO(categoryRepository.save(category));
	}

	public void deleteCategory(Long id) {
		if (!categoryRepository.existsById(id))
			throw new RuntimeException("Category not found");
		categoryRepository.deleteById(id);
	}
}
