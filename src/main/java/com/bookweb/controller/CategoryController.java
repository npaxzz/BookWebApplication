package com.bookweb.controller;

import com.bookweb.dto.CategoryDTO;
import com.bookweb.model.Category;
import com.bookweb.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model,
                       @ModelAttribute("message") String message,
                       @ModelAttribute("error") String error) {
        List<CategoryDTO> dtos = categoryService.getAllCategories()
                .stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
        model.addAttribute("categories", dtos);
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        return "categories/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Category c = categoryService.getCategoryById(id);
            model.addAttribute("category", c); // view.html can display items if entity loaded
            return "categories/view";
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/categories";
        }
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("formAction", "/categories");
        model.addAttribute("submitLabel", "Create");
        return "categories/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("categoryDTO") CategoryDTO dto,
                         BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("formAction", "/categories");
            model.addAttribute("submitLabel", "Create");
            return "categories/form";
        }
        try {
            Category toSave = dto.toEntity();
            // Use service to create — service handles name uniqueness & validation
            Category saved = categoryService.createCategory(toSave.getName(), toSave.getDescription());
            // ensure isActive from DTO is applied if needed
            if (dto.getIsActive() != null && !dto.getIsActive().equals(saved.getIsActive())) {
                saved.setIsActive(dto.getIsActive());
                categoryService.saveCategory(saved);
            }
            ra.addFlashAttribute("message", "Category created: " + saved.getName());
            return "redirect:/categories";
        } catch (RuntimeException ex) {
            model.addAttribute("formAction", "/categories");
            model.addAttribute("submitLabel", "Create");
            model.addAttribute("error", ex.getMessage());
            return "categories/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Category existing = categoryService.getCategoryById(id);
            CategoryDTO dto = CategoryDTO.fromEntity(existing);
            model.addAttribute("categoryDTO", dto);
            model.addAttribute("formAction", "/categories/" + id);
            model.addAttribute("submitLabel", "Update");
            return "categories/form";
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/categories";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("categoryDTO") CategoryDTO dto,
                         BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("formAction", "/categories/" + id);
            model.addAttribute("submitLabel", "Update");
            return "categories/form";
        }
        try {
            Category existing = categoryService.getCategoryById(id);
            dto.updateEntity(existing);
            Category updated = categoryService.updateCategory(id, existing);
            ra.addFlashAttribute("message", "Category updated: " + updated.getName());
            return "redirect:/categories";
        } catch (RuntimeException ex) {
            model.addAttribute("formAction", "/categories/" + id);
            model.addAttribute("submitLabel", "Update");
            model.addAttribute("error", ex.getMessage());
            return "categories/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            categoryService.deleteCategory(id);
            ra.addFlashAttribute("message", "Category deleted");
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/categories";
    }

    @PostMapping("/{id}/force-delete")
    public String forceDelete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            categoryService.forceDeleteCategory(id);
            ra.addFlashAttribute("message", "Category force-deleted");
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/categories";
    }
}