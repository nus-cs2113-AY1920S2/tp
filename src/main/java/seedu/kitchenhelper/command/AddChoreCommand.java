package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.Date;

/**
 * Adds a chore to the chore list.
 */
public class AddChoreCommand extends Command {

    public static final String COMMAND_WORD = "addchore";
    public static final String MESSAGE_SUCCESS = "You have added this chore:\n%s\nNow you have %s chore%s in the list.";
    public static final String COMMAND_DESC = "Adds a chore to the chore list.";
    public static final String COMMAND_PARAMETER = "TASK /by DEADLINE OR TASK /by <dd/MM/yyyy HH:mm>";
    public static final String COMMAND_EXAMPLE = "Example: addchore buy groceries /by Tuesday 12pm "
            + "OR addchore buy groceries /by 14/04/2020 12:00";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);

    private String description;
    private String dateStr = null;
    private Date date = null;


    /**
     * Constructor for AddChoreCommand.
     *
     * @param description the description of the chore.
     * @param dateStr the deadline in String.
     */
    public AddChoreCommand(String description, String dateStr) {
        this.description = description;
        this.dateStr = dateStr;
    }

    /**
     * Constructor for AddChoreCommand.
     *
     * @param description the description of the chore.
     * @param date the deadline as Java Date object.
     */
    public AddChoreCommand(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Gets the String deadline of this chore or empty String if Date deadline.
     *
     * @return the deadline in String or empty String.
     */
    public String getDateStr() {
        if (dateStr == null) {
            return "";
        }
        return dateStr;
    }

    /**
     * Adds a chore to the chore list.
     *
     * @param choreList the ArrayList of chores.
     * @return success message of addition.
     */
    public String addChore(ArrayList<Chore> choreList) {
        Chore newChore;
        if (dateStr == null) {
            newChore = new Chore(description, date);
        } else {
            newChore = new Chore(description, dateStr);
        }
        choreList.add(newChore);
        Storage.saveChoreData(choreList);
        return String.format(MESSAGE_SUCCESS, newChore, choreList.size(), newChore.checkSingular(choreList));
    }

    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList list of recipes.
     * @param choreList list of chores.
     * @return the success message of adding chore to the chore list.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        String feedbackToUser = addChore(choreList);
        return new CommandResult(feedbackToUser);
    }
}
