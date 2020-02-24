package de.sopro.repository;

import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.sopro.model.Author;
import de.sopro.model.Book;
import de.sopro.repository.BookRepository;

@Component
public class AuthorAndBookRepositoryBDDSteps {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	// saving the state between steps
	private Author author;

	@Given("an author")
	public void givenAnISBNFormatChecker()
	{
		author = new Author("Anybody", "Alice");
		author.getBooks().add(new Book(author, "A book by Alice", "0000000000000", 1));
		author.getBooks().add(new Book(author, "Another book by Alice", "0000000000017", 1));
	}

	@When("author is persisted")
	public void whenAuthorIsPersisted()
	{
		author = authorRepository.save(author);
	}

	@Then("books are persisted with author")
	public void thenBooksArePersistedWithAuthor()
	{
		// Check if books belonging to an author have also been persisted
		// when the author was added to the repository

		assertTrue("Author #" + author.getId() + " exists in database",
				authorRepository.existsById(author.getId()));
		for (Book book : author.getBooks()) {
		assertTrue("Book #" + book.getId() + " exists in database",
					bookRepository.existsById(book.getId()));
		}
	}

}
