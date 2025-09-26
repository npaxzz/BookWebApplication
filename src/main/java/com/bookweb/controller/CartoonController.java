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
//แสดงรายละเอียด ( ถ้าใช้ ไม่ใช้ก็ได้มั้ง)
    @GetMapping("/cartoons/{id}")
public String getCartoonById(@PathVariable Long id, Model model) {
    Cartoon cartoon = cartoonRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid cartoon Id:" + id));
    model.addAttribute("cartoon", cartoon);
    return "cartoon-detail"; // cartoon-detail.html (ถ้าใช้)
}
}
