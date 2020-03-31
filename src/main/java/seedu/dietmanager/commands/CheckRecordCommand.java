package seedu.dietmanager.commands;

import seedu.dietmanager.DailyFoodRecord;
import seedu.dietmanager.Food;
import seedu.dietmanager.Profile;
import seedu.dietmanager.Weekday;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class CheckRecordCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;
    private String date;
    private String mealType;
    private boolean noDescription;
    private boolean isInvalidDate;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     * @param description the description of the command.
     */

    public CheckRecordCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;
        this.isInvalidDate = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.date = descriptionArray[0].toUpperCase();
            this.mealType = descriptionArray[1];
            Weekday.valueOf(this.date);
        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (IllegalArgumentException e) {
            this.isInvalidDate = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
            return;
        } else if (this.isInvalidDate) {
            this.result = MessageBank.INVALID_DATE_MESSAGE;
            return;
        }

        DailyFoodRecord record = profile.getRecordOfDay(date);

        switch (mealType) {
        case "morning":
            this.result = date + " Morning: " + System.lineSeparator() + record.showBreakfast()
                    + record.showDailyCalories(mealType);
            break;
        case "afternoon":
            this.result = date + " Afternoon: " + System.lineSeparator() + record.showLunch()
                    + record.showDailyCalories(mealType);
            break;
        case "night":
            this.result = date + " Night: " + System.lineSeparator() + record.showDinner()
                    + record.showDailyCalories(mealType);
            break;
        default:
            this.result = MessageBank.MEAL_TYPE_ERROR;
            break;
        }
    }
}
