package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidWeightException;

import java.util.Optional;

/**
 * WeightParser is the public class responsible for validating the user input and
 * parsing it into a valid Weight.
 */

public class WeightParser {

    /**
     * Minimum boundary Weight value.
     */

    private static double MIN_WEIGHT = 0.00;

    /**
     * Maximum boundary Weight value.
     */

    private static double MAX_WEIGHT = 500.00;

    /**
     * Weight value parsed from user input.
     */

    private static Optional<Double> weight;

    /**
     * Validate the user input and parsing it into a valid Weight.
     *
     * @param description User input.
     * @return Weight in standard form.
     * @throws InvalidWeightException If input is not a valid Weight.
     */

    public static double parseWeight(String description) throws InvalidWeightException {
        try {
            weight = Optional.ofNullable(Double.parseDouble(description));
        } catch (NumberFormatException e) {
            throw new InvalidWeightException();
        }
        if (weight.isEmpty() || (weight.get() <= MIN_WEIGHT || weight.get() >= MAX_WEIGHT)) {
            throw new InvalidWeightException();
        }
        testAssertions();
        return weight.get();
    }

    /**
     * Assertion testing for Weight.
     */

    public static void testAssertions() {
        assert (weight.isPresent());
        assert (weight.get() > MIN_WEIGHT);
        assert (weight.get() < MAX_WEIGHT);
    }

}
