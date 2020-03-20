package seedu.dietmanager.commands;

import seedu.dietmanager.DailyFoodRecord;
import seedu.dietmanager.Food;
import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class CheckRecordCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;
    private String date;
    private String mealType;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     * @param description the description of the command.
     */

    public CheckRecordCommand(String command, String description) throws InvalidFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.date = descriptionArray[0];
        this.mealType = descriptionArray[1];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        DailyFoodRecord record = profile.getRecordOfDay(date);
        switch (mealType) {
        case "breakfast":
            this.result = String.format("Your breakfast for %s:",date) + System.lineSeparator()
                    + record.showBreakfast();
            break;
        case "lunch":
            this.result = String.format("Your lunch for %s:",date) + System.lineSeparator() + record.showLunch();
            break;
        case "dinner":
            this.result = String.format("Your dinner for %s:",date) + System.lineSeparator() + record.showDinner();
            break;
        default:
            this.result = MessageBank.MEAL_TYPE_ERROR;
            break;
        }
        this.result = this.result + ".";
    }
}
