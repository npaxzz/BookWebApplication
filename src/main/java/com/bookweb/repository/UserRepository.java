package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookweb.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
