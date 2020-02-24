package de.sopro.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.sopro.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByIsbn(String isbn);

}
