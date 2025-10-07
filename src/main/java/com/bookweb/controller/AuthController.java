package com.bookweb.controller;

import com.bookweb.model.User;
import com.bookweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            User user = userService.loginUser(email, password);
            session.setAttribute("user", user);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        try {
            userService.registerUser(username, email, password);
            model.addAttribute("success", "Registration successful! Please log in.");
            return "login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // 🚪 ออกจากระบบ
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
