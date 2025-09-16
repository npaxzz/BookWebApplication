package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookweb.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
