package com.bookweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookweb.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
