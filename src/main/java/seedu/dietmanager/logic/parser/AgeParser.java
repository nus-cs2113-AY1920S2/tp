package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidAgeException;
import seedu.dietmanager.commons.exceptions.InvalidHeightException;

public class AgeParser {

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidHeightException If input is not a valid height value.
     */

    public static int parseAge(String description) throws InvalidAgeException {
        int age = 0;
        try {
            age = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException();
        }
        if (age <= 0 || age >= 100) {
            throw new InvalidAgeException();
        }
        testAssertions(age);
        return age;
    }

    public static void testAssertions(int age) {
        assert (age > 0);
        assert (age < 100);
    }

}
