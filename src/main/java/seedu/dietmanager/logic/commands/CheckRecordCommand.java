package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.core.Weekday;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
import seedu.dietmanager.model.DailyFoodRecord;
import seedu.dietmanager.model.Profile;
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
     * @param command     the command prompt entered by the user.
     * @param description the description of the command.
     */

    public CheckRecordCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;
        this.isInvalidDate = false;

        try {
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.date = descriptionArray[0].trim().toUpperCase();
            this.mealType = descriptionArray[1].trim().toLowerCase();
            Weekday.valueOf(this.date);
        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (IllegalArgumentException e) {
            this.isInvalidDate = true;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
            return new Result(this.resultString);
        } else if (this.isInvalidDate) {
            this.resultString = MessageBank.INVALID_DATE_MESSAGE;
            return new Result(this.resultString);
        }

        DailyFoodRecord record = profile.getRecordOfDay(date);

        switch (mealType) {
        case "morning":
            this.resultString = date + " Morning:" + System.lineSeparator() + record.showBreakfast()
                    + record.showDailyCalories(mealType);
            break;
        case "afternoon":
            this.resultString = date + " Afternoon:" + System.lineSeparator() + record.showLunch()
                    + record.showDailyCalories(mealType);
            break;
        case "night":
            this.resultString = date + " Night:" + System.lineSeparator() + record.showDinner()
                    + record.showDailyCalories(mealType);
            break;
        default:
            this.resultString = MessageBank.MEAL_TYPE_ERROR;
            break;
        }
        return new Result(this.resultString);
    }
}
