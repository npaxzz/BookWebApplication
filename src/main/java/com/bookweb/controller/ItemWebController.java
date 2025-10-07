package com.bookweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookweb.dto.ItemDTO;
import com.bookweb.model.Category;
import com.bookweb.model.User;
import com.bookweb.repository.CategoryRepository;
import com.bookweb.service.ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/items")
public class ItemWebController {

	private final ItemService itemService;
	private final CategoryRepository categoryRepository;

	public ItemWebController(ItemService itemService, CategoryRepository categoryRepository) {
		this.itemService = itemService;
		this.categoryRepository = categoryRepository;
	}

	private List<ItemDTO> safeList(List<ItemDTO> list) {
		return list != null ? list : new ArrayList<>();
	}

	@GetMapping("/book")
	public String allBooks(Model model) {
		List<ItemDTO> books = safeList(itemService.getAllItems().stream()
				.filter(i -> "BOOK".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", books);
		return "book"; // templates/book.html
	}

	@GetMapping("/movie")
	public String allMovies(Model model) {
		List<ItemDTO> movies = safeList(itemService.getAllItems().stream()
				.filter(i -> "MOVIE".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", movies);
		return "movie"; // templates/movie.html
	}

	@GetMapping("/cartoon")
	public String allCartoons(Model model) {
		List<ItemDTO> cartoons = safeList(itemService.getAllItems().stream()
				.filter(i -> "CARTOON".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", cartoons);
		return "cartoon"; // templates/cartoon.html
	}

	@GetMapping("/game")
	public String allGames(Model model) {
		List<ItemDTO> games = safeList(itemService.getAllItems().stream()
				.filter(i -> "GAME".equalsIgnoreCase(i.getType())).collect(Collectors.toList()));
		model.addAttribute("items", games);
		return "game"; // templates/game.html
	}

	@GetMapping("/category")
	public String allCategories(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "category";
	}

	@GetMapping("/new")
	public String showAddItemForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		User currentUser = (User) session.getAttribute("user");
		if (currentUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "❌ กรุณาเข้าสู่ระบบก่อนเพิ่มรายการ!");
			return "redirect:/login";
		}

		model.addAttribute("item", new ItemDTO());
		model.addAttribute("categories", itemService.getAllCategories());
		return "new-item";
	}

	@PostMapping("/add")
	public String addItem(@ModelAttribute("item") ItemDTO itemDTO,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile, HttpSession session,
			RedirectAttributes redirectAttributes) {
		User currentUser = (User) session.getAttribute("user");
		if (currentUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "❌ กรุณาเข้าสู่ระบบก่อนเพิ่มรายการ!");
			return "redirect:/login";
		}

		try {
			itemService.addItem(itemDTO, imageFile, currentUser); // ส่ง currentUser ไปบันทึกเป็นเจ้าของ
			redirectAttributes.addFlashAttribute("successMessage", "✅ เพิ่มรายการสำเร็จแล้ว!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "❌ เพิ่มรายการไม่สำเร็จ: " + e.getMessage());
		}

		return "redirect:/items/new";
	}

	@GetMapping("/{id}")
	public String itemDetail(@PathVariable Long id, Model model, HttpSession session) {
		ItemDTO item = itemService.getItemById(id);
		model.addAttribute("item", item);

		User currentUser = (User) session.getAttribute("user");
		model.addAttribute("currentUserId", currentUser != null ? currentUser.getId() : null);

		return "itemDetail";
	}

	@PostMapping("/{id}/delete")
	public String deleteItem(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
		User currentUser = (User) session.getAttribute("user");
		if (currentUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "❌ ต้องล็อกอินก่อนทำการลบ");
			return "redirect:/login";
		}

		ItemDTO item = itemService.getItemById(id);

		if (!currentUser.getId().equals(item.getOwnerId())) {
			redirectAttributes.addFlashAttribute("errorMessage", "❌ คุณลบไอเท็มนี้ไม่ได้");
			return "redirect:/items/category";
		}

		itemService.deleteItem(id);
		redirectAttributes.addFlashAttribute("successMessage", "✅ ลบไอเท็มสำเร็จ");
		return "redirect:/items/category";
	}

}
