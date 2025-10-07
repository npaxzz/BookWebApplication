package com.bookweb.call;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/call")
public class CallController {

	@GetMapping("/{itemId}")
	public String callRoom(@PathVariable("itemId") int itemId, Model model, HttpSession session) {
		model.addAttribute("itemId", itemId);
		String username = (session.getAttribute("username") != null) ? session.getAttribute("username").toString()
				: "Guest";
		model.addAttribute("username", username);
		return "call";
	}

}
