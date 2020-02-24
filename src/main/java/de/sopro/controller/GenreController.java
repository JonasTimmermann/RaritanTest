package de.sopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.sopro.model.Book;
import de.sopro.model.Genre;
import de.sopro.repository.BookRepository;
import de.sopro.repository.GenreRepository;

@Controller
public class GenreController {

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/genres")
	public String showGenreList(Model model) {
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("allBooks", bookRepository.findAll());
		return "genres-list";
	}

	@PostMapping("/genres/add")
	public String saveGenre(@RequestParam String genreName, Model model) {
		if (!genreName.isEmpty()) {
			genreRepository.save(new Genre(genreName));
		}
		return "redirect:/genres";
	}

	@GetMapping("/genre/{id}/delete")
	public String deleteGenre(@PathVariable Long id, Model model) {
		// Remove genre from all books
		Genre genre = genreRepository.findById(id).get();
		for (Book book : genre.getBooks()) {
			book.getGenres().remove(genre);
			bookRepository.save(book);
		}
		// Delete genre from repository
		genreRepository.delete(genre);
		return "redirect:/genres";
	}

	@PostMapping("/genres/addbook")
	public String addBookToGenre(@RequestParam Long genreId, @RequestParam Long bookId) {
		Genre genre = genreRepository.findById(genreId).get();
		Book book = bookRepository.findById(bookId).get();
		book.getGenres().add(genre);
		bookRepository.save(book);
		return "redirect:/genres";
	}

	@PostMapping("/genres/removebook")
	public String removeBookFromGenre(@RequestParam Long genreId, @RequestParam Long bookId) {
		Genre genre = genreRepository.findById(genreId).get();
		Book book = bookRepository.findById(bookId).get();
		book.getGenres().remove(genre);
		bookRepository.save(book);
		return "redirect:/genres";
	}

}