package seedu.dietmanager.logic.parser;

import seedu.dietmanager.commons.exceptions.InvalidFoodNameException;
import seedu.dietmanager.commons.exceptions.InvalidNameException;

public class FoodNameParser {

    /**
     * Validate the user input gender and parse it into the standard gender accepted.
     *
     * @param description User input gender.
     * @return Gender in standard form.
     * @throws InvalidNameException If input is not a valid height value.
     */

    public static String parseFoodName(String description) throws InvalidFoodNameException {
        String foodName = "";
        foodName = description.trim().toLowerCase();
        if (foodName.equals("")) {
            throw new InvalidFoodNameException();
        }
        testAssertions(foodName);
        foodName = foodName.replaceAll(" ", "-");
        return foodName;
    }

    public static void testAssertions(String foodName) {
        assert (!(foodName.equals("")));
    }

}
