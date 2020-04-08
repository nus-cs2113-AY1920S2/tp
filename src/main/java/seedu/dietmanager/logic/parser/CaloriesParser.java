package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidCaloriesException;

import java.util.Optional;

/**
 * CaloriesParser is the public class responsible for validating the user input and
 * parsing it into a valid Calorie value.
 */

public class CaloriesParser {

    /**
     * Minimum boundary Calorie value.
     */

    private static double MIN_CALORIES = 0.00;

    /**
     * Calorie value parsed from user input.
     */

    private static Optional<Double> calories;

    /**
     * Validate the user input and parsing it into a valid Calorie value.
     *
     * @param description User input.
     * @return Calories in standard form.
     * @throws InvalidCaloriesException If input is not a valid Calorie value.
     */

    public static double parseCalories(String description) throws InvalidCaloriesException {
        try {
            calories = Optional.ofNullable(Double.parseDouble(description));
        } catch (NumberFormatException e) {
            throw new InvalidCaloriesException();
        }
        if (calories.isEmpty() || calories.get() < MIN_CALORIES) {
            throw new InvalidCaloriesException();
        }
        testAssertions();
        return calories.get();
    }

    /**
     * Assertion testing for Calories.
     */

    public static void testAssertions() {
        assert (calories.isPresent());
        assert (calories.get() >= MIN_CALORIES);
    }

}
