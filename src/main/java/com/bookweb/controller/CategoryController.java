package com.bookweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookweb.model.Book;
import com.bookweb.model.Category;
import com.bookweb.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;

	// Single Responsibility (S จาก SOLID)
	// Controller มีหน้าที่ handle HTTP request อย่างเดียว
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// GET: ดึง category ทั้งหมด
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	// GET: ดึง category ตาม id
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	// POST: เพิ่ม category ใหม่
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}

	// PUT: แก้ไข category
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
		Category category = categoryService.getCategoryById(id);
		category.setName(categoryDetails.getName());
		return categoryService.saveCategory(category);
	}

	// DELETE: ลบ category
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return "Category deleted successfully";
	}

	// GET: ดึงหนังสือตาม category
	@GetMapping("/{id}/books")
	public List<Book> getBooksByCategory(@PathVariable Long id) {
		return categoryService.getBooksByCategory(id);
	}
}