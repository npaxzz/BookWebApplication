package com.bookweb.controller;

import com.bookweb.model.Game;
import com.bookweb.repository.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    // ✅ แสดงรายการเกมทั้งหมด
    @GetMapping("/games")
    public String getAllGames(Model model) {
        List<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "games"; // 🔹 ชื่อไฟล์ view: games.html
    }

    // ✅ แสดงรายละเอียดเกม (เลือกใช้ก็ได้)
    @GetMapping("/games/{id}")
    public String getGameById(@PathVariable Long id, Model model) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id: " + id));
        model.addAttribute("game", game);
        return "game-detail"; // 🔹 ชื่อไฟล์ view: game-detail.html
    }
}