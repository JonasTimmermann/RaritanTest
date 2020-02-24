package de.sopro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "genres")
	private List<Book> books;

	public Genre() {}

	public Genre(String name) {
		this.name = name;
		this.books = new ArrayList<Book>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getNumberOfBooks() {
		return books.size();
	}

	public boolean containsBook(Book book) {
		for (Book b : books) {
			if (b.getId() == book.getId()) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return name;
	}

}
