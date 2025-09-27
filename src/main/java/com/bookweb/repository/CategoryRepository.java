package com.bookweb.repository;

import com.bookweb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
    
    boolean existsByName(String name);

    List<Category> findByNameContaining(String keyword);

    List<Category> findByDescriptionContaining(String keyword);
}
