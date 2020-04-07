package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidWeightException;

public class WeightParser {

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidWeightException If input is not a valid height value.
     */

    public static double parseWeight(String description) throws InvalidWeightException {
        double weight = 0.00;
        try {
            weight = Double.parseDouble(description);
        } catch (NumberFormatException e) {
            throw new InvalidWeightException();
        }
        if (weight <= 0.00 || weight >= 500.00) {
            throw new InvalidWeightException();
        }
        testAssertions(weight);
        return weight;
    }

    public static void testAssertions(double weight) {
        assert (weight > 0.00);
        assert (weight < 500.00);
    }

}
