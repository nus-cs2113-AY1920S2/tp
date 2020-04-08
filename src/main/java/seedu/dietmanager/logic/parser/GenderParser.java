package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidGenderException;

import java.util.Optional;

/**
 * GenderParser is the public class responsible for validating the user input and
 * parsing it into a valid gender.
 */

public class GenderParser {

    /**
     * Standard form for Male gender.
     */

    private static final String MALE = "male";

    /**
     * Standard form for female gender.
     */

    private static final String FEMALE = "female";

    /**
     * gender value parsed from user input.
     */

    private static Optional<String> gender;

    /**
     * Validate the user input and parsing it into a valid gender.
     *
     * @param description User input.
     * @return Gender in standard form.
     * @throws InvalidGenderException If input is not a valid Gender.
     */

    public static String parseGender(String description) throws InvalidGenderException {
        gender = Optional.ofNullable(description.trim().toLowerCase());
        if (gender.isEmpty() || !(gender.get().equals(MALE) || gender.get().equals(FEMALE))) {
            throw new InvalidGenderException();
        }
        testAssertions();
        return gender.get();
    }

    /**
     * Assertion testing for Gender.
     */

    public static void testAssertions() {
        assert (gender.isPresent());
        assert ((gender.get().equals(MALE)) || (gender.get().equals(FEMALE)));
    }

}
