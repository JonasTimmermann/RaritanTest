package de.sopro.model;

public final class IsbnFormatChecker {

	private IsbnFormatChecker() {}

	private static String patternIsbn = "\\d{3}+\\-?\\d+\\-?\\d+\\-?\\d+-?\\d";

	/** @brief Check if given string satisfies the ISBN-13 format and converts
	           it to a unified representation consisting of 13 digits only.
		@return Returns formatted string or empty string if format is invalid.
	 */
	public static String getFormattedIsbn(String isbn) {

		if (isbn.matches(patternIsbn)) {
			String result = isbn.replaceAll("\\-", "");
			if (result.length() == 13) {
				return result;
			}
		}

		return "";
	}

}
