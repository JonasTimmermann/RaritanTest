package de.sopro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.sopro.model.Author;
import de.sopro.model.Book;
import de.sopro.model.Genre;
import de.sopro.repository.AuthorRepository;
import de.sopro.repository.BookRepository;
import de.sopro.repository.GenreRepository;

@SpringBootApplication
public class SoproSpringDemoApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	GenreRepository genreRepository;

	public static void main(String[] args) {
		SpringApplication.run(SoproSpringDemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		// If there is no data, add some initial values for testing the application.
		// ATTENTION: If you change any model (i.e., the data scheme), you most likely
		// need to delete the .h2 database file in your file system first!

		if (bookRepository.count() == 0 && authorRepository.count() == 0 && genreRepository.count() == 0) {

			// Create and save genres
			Genre genre1 = new Genre("Fantasy");
			Genre genre2 = new Genre("Horror");
			Genre genre3 = new Genre("Computer Science");
			Genre genre4 = new Genre("Nonfiction");
			genreRepository.save(genre1);
			genreRepository.save(genre2);
			genreRepository.save(genre3);
			genreRepository.save(genre4);

			// Create author with single book
			Author author1 = new Author("Ping", "Stephen");
			Book book1 = new Book(author1, "Nerd Semetary", "1234567890128", 1000);
			author1.getBooks().add(book1);

			// Create author with two books
			Author author2 = new Author("Growling", "Joanne K.");
			Book book2a = new Book(author2, "Larry Snotter and the Philosopher's Scone", "8422543849311", 1239);
			Book book2b = new Book(author2, "Larry Snotter and the Chamber of Biscuits", "8422543849328", 1679);
			author2.getBooks().add(book2a);
			author2.getBooks().add(book2b);

			// Create author with three books
			Author author3 = new Author("Avalanche", "Monsieur");
			Book book3a = new Book(author3, "These Spring Boots are made for walking", "5217123017326", 711);
			Book book3b = new Book(author3, "A Craft of Coffee", "5217123017333", 911);
			Book book3c = new Book(author3, "A Cosmos of Caffeine", "5217123017340", 1111);
			author3.getBooks().add(book3a);
			author3.getBooks().add(book3b);
			author3.getBooks().add(book3c);

			// Add genres to books
			book1.getGenres().add(genre2);
			book2a.getGenres().add(genre1);
			book2b.getGenres().add(genre1);			
			book2b.getGenres().add(genre2);
			book3a.getGenres().add(genre3);
			book3a.getGenres().add(genre4);
			book3b.getGenres().add(genre4);
			book3c.getGenres().add(genre4);

			// Save authors and books
			authorRepository.save(author1);
			authorRepository.save(author2);
			authorRepository.save(author3);

		}
	}

}
