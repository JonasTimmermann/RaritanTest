package de.sopro.model;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;

@Component
public class IsbnFormatCheckerBDDSteps {

	// saving the state between steps
	private String formattedIsbn;

	@Given("an ISBN format checker")
	public void givenAnISBNFormatChecker() {
		// In this case nothing has to be initialized, because
		// the checker is realized using a static method
	}

	@When("ISBN \"$isbn\" is formatted")
	public void whenISBNIsFormatted(String isbn) {
		formattedIsbn = IsbnFormatChecker.getFormattedIsbn(isbn);
	}

	@When("empty ISBN is formatted")
	public void whenEmptyISBNIsFormatted() {
		// reusing other step
		whenISBNIsFormatted("");
	}

	@Then("ISBN equals \"$expected_isbn\"")
	public void thenISBNEquals(String expectedIsbn) {
		assertEquals(formattedIsbn, expectedIsbn);
	}

	@Then("ISBN is empty")
	public void thenISBNIsEmpty() {
		// reusing other step
		thenISBNEquals("");
	}

}
