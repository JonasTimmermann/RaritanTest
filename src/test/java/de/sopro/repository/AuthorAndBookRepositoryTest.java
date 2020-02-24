package de.sopro.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.sopro.model.Author;
import de.sopro.model.Book;
import de.sopro.repository.AuthorRepository;
import de.sopro.repository.BookRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AuthorAndBookRepositoryTest {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	private Author author;

	@Before
	public void addAuthorAndBooks() {

		// Add test data to repository before running each test

		author = new Author("Anybody", "Alice");
		author.getBooks().add(new Book(author, "A book by Alice", "0000000000000", 1));
		author.getBooks().add(new Book(author, "Another book by Alice", "0000000000017", 1));
		author = authorRepository.save(author);
	}

	@Test
	public void testBooksArePersistedWithAuthor() throws Exception {

		// Check if books belonging to an author have also been persisted
		// when the author was added to the repository

		assertTrue("Author #" + author.getId() + " exists in database",
				authorRepository.existsById(author.getId()));
		for (Book book : author.getBooks()) {
			assertTrue("Book #" + book.getId() + " exists in database",
					bookRepository.existsById(book.getId()));
		}
	}

	@Test
	public void testBooksAreDeletedWithAuthor() throws Exception {

		// Get ID of author and collect IDs of all books belonging to them
		// (second part could also use a for loop, but this is Java 8 style)

		Long authorId = author.getId();
		List<Long> bookIds = author.getBooks().stream().map(Book::getId).collect(Collectors.toList());

		// Check if books are deleted with the author they belong to
		authorRepository.delete(author);
		author = null;
		assertFalse("Author #" + authorId + " is not in database",
				authorRepository.existsById(authorId));
		for (Long bookId : bookIds) {
			assertFalse("Book #" + bookId + " is not in database",
					bookRepository.existsById(bookId));
		}
	}

}
