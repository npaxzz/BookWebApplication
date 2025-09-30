package com.bookweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookweb.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryWebController {

	private final CategoryService categoryService;

	public CategoryWebController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// แสดง Category ทั้งหมด
	@GetMapping
	public String allCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories"; // templates/categories.html
	}

	// แสดงรายการ Item ตาม Category
	@GetMapping("/{id}")
	public String categoryItems(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.getCategoryById(id));
		return "categoryDetail"; // templates/categoryDetail.html
	}
}
