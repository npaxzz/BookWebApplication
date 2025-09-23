package com.bookweb.service;

import com.bookweb.model.Book;
import com.bookweb.model.Category;
import com.bookweb.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // ✅ Dependency Inversion (D จาก SOLID)
    // พึ่งพา interface (CategoryRepository) ไม่ใช่ implementation
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // ดึง Category ทั้งหมด
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ดึง Category ตาม id
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    // สร้าง/แก้ไข Category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // ลบ Category
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    // ดึง Book ตาม Category id
    public Set<Book> getBooksByCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        return category.getBooks();
    }
}
