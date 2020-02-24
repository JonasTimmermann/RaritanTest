package de.sopro.repository;

import org.springframework.data.repository.CrudRepository;

import de.sopro.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
