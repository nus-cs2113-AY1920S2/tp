package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidNameException;

import java.util.Optional;

/**
 * NameParser is the public class responsible for validating the user input and
 * parsing it into a valid Name.
 */

public class NameParser {

    /**
     * Name value parsed from user input.
     */

    private static Optional<String> name;

    /**
     * Validate the user input and parsing it into a valid Name.
     *
     * @param description User input.
     * @return Name in standard form.
     * @throws InvalidNameException If input is not a valid Name.
     */

    public static String parseName(String description) throws InvalidNameException {
        name = Optional.ofNullable(description.trim());
        if (name.isEmpty()) {
            throw new InvalidNameException();
        }
        name = Optional.ofNullable(name.get().replaceAll(" ", "-"));
        testAssertions();
        return name.get();
    }

    /**
     * Assertion testing for Name.
     */

    public static void testAssertions() {
        assert (name.isPresent());
    }

}
