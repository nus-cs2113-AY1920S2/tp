package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidHeightException;

import java.util.Optional;

/**
 * HeightParser is the public class responsible for validating the user input and
 * parsing it into a valid Height.
 */

public class HeightParser {

    /**
     * Minimum boundary Height value.
     */

    private static double MIN_HEIGHT = 0.00;

    /**
     * Maximum boundary Height value.
     */

    private static double MAX_HEIGHT = 300.00;

    /**
     * Height value parsed from user input.
     */

    private static Optional<Double> height;

    /**
     * Validate the user input and parsing it into a valid Height.
     *
     * @param description User input.
     * @return Height in standard form.
     * @throws InvalidHeightException If input is not a valid Height.
     */

    public static double parseHeight(String description) throws InvalidHeightException {
        try {
            height = Optional.ofNullable(Double.parseDouble(description));
        } catch (NumberFormatException e) {
            throw new InvalidHeightException();
        }
        if (height.isEmpty() || (height.get() <= MIN_HEIGHT || height.get() >= MAX_HEIGHT)) {
            throw new InvalidHeightException();
        }
        testAssertions();
        return height.get();
    }

    /**
     * Assertion testing for Height.
     */

    public static void testAssertions() {
        assert (height.isPresent());
        assert (height.get() > MIN_HEIGHT);
        assert (height.get() < MAX_HEIGHT);
    }

}
