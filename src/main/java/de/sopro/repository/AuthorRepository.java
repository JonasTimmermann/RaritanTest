package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
