package com.bookweb.controller;

import com.bookweb.model.Cartoon;
import com.bookweb.repository.CartoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartoonController {

    @Autowired
    private CartoonRepository cartoonRepository;

    // แสดงรายการการ์ตูนทั้งหมด
    @GetMapping("/cartoons")
    public String getAllCartoons(Model model) {
        List<Cartoon> cartoons = cartoonRepository.findAll();
        model.addAttribute("cartoons", cartoons);
        return "cartoons"; // cartoons.html
    }
}
