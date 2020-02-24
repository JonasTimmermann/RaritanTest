package de.sopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.model.Book;
import de.sopro.model.Genre;
import de.sopro.repository.BookRepository;
import de.sopro.repository.GenreRepository;

@RestController
public class GenreRestController {

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/api/genre/{id}")
	public Genre getGenre(@PathVariable Long id) {
		// Return genre in repository or empty genre if not found
		Genre genre = genreRepository.findById(id).orElse(null);
		return genre != null ? genre : new Genre("");
	}

	@PostMapping("/api/genre")
	public Genre newGenre(@RequestParam String name) {
		// Create and return genre in repository
		return genreRepository.save(new Genre(name));
	}

	@PutMapping("/api/genre/{id}")
	public Genre updateGenre(@PathVariable Long id, @RequestParam String name) {
		// Update and return genre in repository or empty genre if not found
		Genre genre = genreRepository.findById(id).orElse(null);
		if (genre != null) {
			genre.setName(name);
			return genreRepository.save(genre);
		}
		return new Genre("");
	}

	@DeleteMapping("/api/genre/{id}")
	public Genre deleteGenre(@PathVariable Long id) {
		// Delete genre in repository and return empty genre
		Genre genre = genreRepository.findById(id).orElse(null);
		if (genre != null) {
			// Remove genre from all books
			for (Book book : genre.getBooks()) {
				book.getGenres().remove(genre);
				bookRepository.save(book);
			}
			// Delete genre from repository
			genreRepository.delete(genre);
		}
		return new Genre("");
	}

}
