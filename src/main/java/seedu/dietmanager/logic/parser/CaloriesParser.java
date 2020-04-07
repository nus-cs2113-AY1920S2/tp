package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidCaloriesException;
import seedu.dietmanager.commons.exceptions.InvalidHeightException;

public class CaloriesParser {

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidHeightException If input is not a valid height value.
     */

    public static double parseCalories(String description) throws InvalidCaloriesException {
        double calories = -1.00;
        try {
            calories = Double.parseDouble(description);
        } catch (NumberFormatException e) {
            throw new InvalidCaloriesException();
        }
        if (calories < 0.00) {
            throw new InvalidCaloriesException();
        }
        testAssertions(calories);
        return calories;
    }

    public static void testAssertions(double calories) {
        assert (calories >= 0.00);
    }

}
