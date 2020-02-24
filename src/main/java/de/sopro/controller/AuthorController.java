package de.sopro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.sopro.model.Author;
import de.sopro.repository.AuthorRepository;

@Controller
public class AuthorController {

	@Autowired
	AuthorRepository authorRepository;

	@GetMapping("/authors")
	public String showAuthorList(Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		return "authors-list";
	}

	@GetMapping("/author/{id}/books")
	public String showBooksOfAuthor(@PathVariable Long id, Model model) {
		model.addAttribute("author", authorRepository.findById(id).get());
		return "author-books";
	}

	@GetMapping("/authors/new")
	public String showNewAuthorPage(Model model) {
		model.addAttribute("author", new Author());
		return "author-new";
	}

	@PostMapping("/authors/save")
	public String saveAuthor(@Valid Author author, BindingResult bindingResult, Model model) {
		// Check if all constraints are met
		if (bindingResult.hasErrors()) {
			model.addAttribute("author", author);
			return "author-new";
		}
		authorRepository.save(author);
		return "redirect:/authors";
	}

	@GetMapping("/author/{id}/delete")
	public String deleteAuthorById(@PathVariable Long id) {
		authorRepository.deleteById(id);
		return "redirect:/authors";
	}

	@GetMapping("/author/{id}/edit")
	public String showAuthorById(@PathVariable Long id, Model model) {
		model.addAttribute("author", authorRepository.findById(id).get());
		return "author-edit";
	}

	@PostMapping("/author/{id}/edit")
	public String updateAuthorById(@PathVariable Long id,
			@Valid Author author, BindingResult bindingResult, Model model) {
		// Check if all constraints are met
		if (bindingResult.hasErrors()) {
			model.addAttribute("author", author);
			return "author-edit";
		}
		author.setId(id);
		authorRepository.save(author);
		return "redirect:/authors";
	}

}