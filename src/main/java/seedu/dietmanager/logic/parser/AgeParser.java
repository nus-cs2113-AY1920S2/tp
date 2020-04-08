package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidAgeException;

import java.util.Optional;

/**
 * AgeParser is the public class responsible for validating the user input and
 * parsing it into a valid Age.
 */

public class AgeParser {

    /**
     * Minimum boundary Age value.
     */

    private static int MIN_AGE = 0;

    /**
     * Maximum boundary Age value.
     */

    private static int MAX_AGE = 150;

    /**
     * Age value parsed from user input.
     */

    private static Optional<Integer> age;

    /**
     * Validate the user input and parsing it into a valid Age.
     *
     * @param description User input.
     * @return Age in standard form.
     * @throws InvalidAgeException If input is not a valid Age.
     */

    public static int parseAge(String description) throws InvalidAgeException {
        try {
            age = Optional.ofNullable(Integer.parseInt(description));
        } catch (NumberFormatException e) {
            throw new InvalidAgeException();
        }
        if (age.isEmpty() || (age.get() <= MIN_AGE || age.get() >= MAX_AGE)) {
            throw new InvalidAgeException();
        }
        testAssertions();
        return age.get();
    }

    /**
     * Assertion testing for Age.
     */

    public static void testAssertions() {
        assert (age.isPresent());
        assert (age.get() > MIN_AGE);
        assert (age.get() < MAX_AGE);
    }

}
