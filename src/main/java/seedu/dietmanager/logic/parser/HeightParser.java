package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidHeightException;

public class HeightParser {

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidHeightException If input is not a valid height value.
     */

    public static double parseHeight(String description) throws InvalidHeightException {
        double height = 0.00;
        try {
            height = Double.parseDouble(description);
        } catch (NumberFormatException e) {
            throw new InvalidHeightException();
        }
        if (height <= 0.00 || height >= 3.00) {
            throw new InvalidHeightException();
        }
        testAssertions(height);
        return height;
    }

    public static void testAssertions(double height) {
        assert (height > 0.00);
        assert (height < 3.00);
    }

}
