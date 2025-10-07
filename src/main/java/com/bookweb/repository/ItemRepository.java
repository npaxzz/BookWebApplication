package com.bookweb.repository;

import com.bookweb.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {}
