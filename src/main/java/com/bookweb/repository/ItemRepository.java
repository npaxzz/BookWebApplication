package com.bookweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookweb.model.Item;
import com.bookweb.model.ItemType;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByTitleContainingIgnoreCase(String title);

	List<Item> findByCreatorContainingIgnoreCase(String creator);

	List<Item> findByType(ItemType type);
}
