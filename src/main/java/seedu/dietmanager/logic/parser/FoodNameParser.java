package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFoodNameException;

import java.util.Optional;

/**
 * FoodNameParser is the public class responsible for validating the user input and
 * parsing it into a valid Food Name.
 */

public class FoodNameParser {

    /**
     * Food Name value parsed from user input.
     */

    private static Optional<String> foodName;

    /**
     * Validate the user input and parsing it into a valid Food Name.
     *
     * @param description User input.
     * @return Food Name in standard form.
     * @throws InvalidFoodNameException If input is not a valid Food Name.
     */

    public static String parseFoodName(String description) throws InvalidFoodNameException {
        foodName = Optional.ofNullable(description.trim().toLowerCase());
        if (foodName.isEmpty()) {
            throw new InvalidFoodNameException();
        }
        foodName = Optional.ofNullable(foodName.get().replaceAll(" ", "-"));
        testAssertions();
        return foodName.get();
    }

    /**
     * Assertion testing for Food Name.
     */

    public static void testAssertions() {
        assert (foodName.isPresent());
    }

}
