package de.sopro.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsbnFormatCheckerParameterizedTest {

	// Set up example values that are bound to givenIsbn and expectedResult for each test
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ "123-4-567-89012-8", "1234567890128" }, { "842-2-543-84931-1", "8422543849311" },
			{ "842-2-543-84932-8", "8422543849328"},  { "521-7-123-01732-6", "5217123017326" },
			{ "", "" }, { "456", "" }, { "123-4-567-89012-x", "" }, { "abc-4-567-89012-3", "" },
			{ "12-34-567-89012-8", "" }, {"521-7-123-0173-0", "" },  { "123-4-567-89012--3", "" }
		});
	}

	@Parameter(0)
	public String givenIsbn;

	@Parameter(1)
	public String expectedResult;

	@Test
	public void testIfIsbnsAreFormattedCorrectly() throws Exception {

		// Check if valid ISBNs are converted to 13 digit strings and invalid ISBNs are
		// converted to the empty string by IsbnFormatChecker.getFormattedIsbn()

		assertEquals("Test if \"" + givenIsbn + "\" is formatted to \"" + expectedResult + "\"",
				expectedResult, IsbnFormatChecker.getFormattedIsbn(givenIsbn));
	}

}
