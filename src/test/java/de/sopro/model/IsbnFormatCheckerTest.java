package de.sopro.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsbnFormatCheckerTest {

	@Test
	public void testIfGoodIsbnIsFormattedCorrectly() throws Exception {

		assertEquals("1234567890128", IsbnFormatChecker.getFormattedIsbn("123-4-567-89012-8"));

	}

	@Test
	public void testIfFormattedIsbnIsFormattedCorrectly() throws Exception {

		assertEquals("1234567890128", IsbnFormatChecker.getFormattedIsbn("1234567890128"));

	}

	@Test
	public void testIfBadIsbnAreFormattedCorrectly() throws Exception {

		assertEquals("", IsbnFormatChecker.getFormattedIsbn("123-4-567-89012-x"));

	}

	@Test
	public void testIfEmptyIsbnAreFormattedCorrectly() throws Exception {

		assertEquals("", IsbnFormatChecker.getFormattedIsbn(""));

	}

}
