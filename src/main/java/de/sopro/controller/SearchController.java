package de.sopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.sopro.model.Book;
import de.sopro.model.IsbnFormatChecker;
import de.sopro.repository.BookRepository;

@Controller
public class SearchController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/search")
	public String showSearch(Model model) {
		return "search";
	}

	@PostMapping("/search")
	public String showSearchResults(@RequestParam String isbn, Model model) {
		String formattedIsbn = IsbnFormatChecker.getFormattedIsbn(isbn);
		model.addAttribute("isbn", isbn);
		model.addAttribute("isbnValid", !formattedIsbn.isEmpty());
		model.addAttribute("books", bookRepository.findByIsbn(formattedIsbn));
		return "search";
	}

	@GetMapping("/api/search/{isbn}")
	@ResponseBody
	public List<Book> getSearchResults(@PathVariable String isbn) {
		String formattedIsbn = IsbnFormatChecker.getFormattedIsbn(isbn);
		return bookRepository.findByIsbn(formattedIsbn);
	}

}
