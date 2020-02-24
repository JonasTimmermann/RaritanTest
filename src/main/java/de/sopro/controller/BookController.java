package de.sopro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.sopro.model.Book;
import de.sopro.repository.AuthorRepository;
import de.sopro.repository.BookRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@GetMapping("/books")
	public String showBookList(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "books-list";
	}

	@GetMapping("/books/new")
	public String showNewBookPage(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("allAuthors", authorRepository.findAll());
		return "book-new";
	}

	@PostMapping("/books/save")
	public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
		// Check if all constraints are met
		if (bindingResult.hasErrors()) {
			model.addAttribute("book", book);
			model.addAttribute("allAuthors", authorRepository.findAll());
			return "book-new";
		}
		bookRepository.save(book);
		return "redirect:/books";
	}

	@GetMapping("/book/{id}/delete")
	public String deleteBookById(@PathVariable Long id) {
		bookRepository.deleteById(id);
		return "redirect:/books";
	}

	@GetMapping("/book/{id}/edit")
	public String showBookById(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookRepository.findById(id).get());
		model.addAttribute("allAuthors", authorRepository.findAll());
		return "book-edit";
	}

	@PostMapping("/book/{id}/edit")
	public String updateBookById(@PathVariable Long id,
			@Valid Book book, BindingResult bindingResult, Model model) {
		// Check if all constraints are met
		if (bindingResult.hasErrors()) {
			model.addAttribute("book", book);
			model.addAttribute("allAuthors", authorRepository.findAll());
			return "book-edit";
		}
		book.setId(id);
		bookRepository.save(book);
		return "redirect:/books";
	}

}