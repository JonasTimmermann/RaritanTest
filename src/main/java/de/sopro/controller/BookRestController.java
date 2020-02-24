package de.sopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.sopro.model.Author;
import de.sopro.model.Book;
import de.sopro.repository.AuthorRepository;
import de.sopro.repository.BookRepository;

@RestController
public class BookRestController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@GetMapping("/api/book/{id}")
	public Book getBook(@PathVariable Long id) {
		// Return book in repository or empty book if not found
		Book book = bookRepository.findById(id).orElse(null);
		return book != null ? book : new Book();
	}

	@PostMapping("/api/book")
	public Book newBook(@RequestParam Long authorId, @RequestParam String title,
			@RequestParam String isbn, @RequestParam int priceInCents) {
		// Create and return book in repository
		Author author = authorRepository.findById(authorId).orElse(null);
		return bookRepository.save(new Book(author, title, isbn, priceInCents));
	}

	@PutMapping("/api/book/{id}")
	public Book updateBook(@PathVariable Long id,
			@RequestParam Long authorId, @RequestParam String title,
			@RequestParam String isbn, @RequestParam int priceInCents) {
		// Update and return book in repository or empty book if not found
		Book book = bookRepository.findById(id).orElse(null);
		if (book != null) {
			Author author = authorRepository.findById(authorId).orElse(null);
			book.setAuthor(author);
			book.setTitle(title);
			book.setIsbn(isbn);
			book.setPriceInCents(priceInCents);
			return bookRepository.save(book);
		}
		return new Book();
	}

	@DeleteMapping("/api/book/{id}")
	public Book deleteBook(@PathVariable Long id) {
		// Delete book in repository and return empty book
		if (bookRepository.existsById(id))
			bookRepository.deleteById(id);
		return new Book();
	}

}
