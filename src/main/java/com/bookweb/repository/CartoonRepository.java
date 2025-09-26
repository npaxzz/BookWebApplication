package com.bookweb.repository;

import com.bookweb.model.Cartoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoonRepository extends JpaRepository<Cartoon, Long> {
}
