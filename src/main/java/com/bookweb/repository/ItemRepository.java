package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookweb.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}