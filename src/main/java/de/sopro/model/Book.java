package de.sopro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Author author;

	@NotEmpty
	private String title;

	@Pattern(regexp = "\\d{13}+")
	private String isbn;

	private int priceInCents;

	@ManyToMany
	private List<Genre> genres;

	public Book() {}

	public Book(Author author, String title, String isbn, int priceInCents) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.priceInCents = priceInCents;
		this.genres = new ArrayList<Genre>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(int priceInCents) {
		this.priceInCents = priceInCents;
	}

	public String getPriceInEuro() {
		return String.format("%d,%02d", priceInCents / 100, priceInCents % 100);
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String toString() {
		return author.getFullName() + ": " + title;
	}

}
