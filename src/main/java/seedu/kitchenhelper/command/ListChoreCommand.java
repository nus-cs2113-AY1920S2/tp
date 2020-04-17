package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;

/**
 * Displays the list of chores.
 */
public class ListChoreCommand extends Command {


    public static final String COMMAND_WORD = "listchore";
    public static final String COMMAND_DESC = "Display all the chores.";
    public static final String COMMAND_FORMAT = "listchore *No parameter";
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Example: %s", COMMAND_WORD);

    /**
     * Loops through and displays each chore in the list.
     *
     * @param choreList the ArrayList of chores.
     * @return empty list or the list of chores.
     */
    public String listChore(ArrayList<Chore> choreList) {
        String feedbackToUser = "";
        if (choreList.size() == 0) {
            feedbackToUser = "Your list of chores is currently empty.";
        } else {
            feedbackToUser = "Here are the chores in your list:";
            for (int i = 0; i < choreList.size(); ++i) {
                feedbackToUser += ("\n" + Integer.toString(i + 1) + ". " + choreList.get(i));
            }
        }
        return feedbackToUser;
    }

    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList list of recipes.
     * @param choreList list of chores.
     * @return the list of chores.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        String feedbackToUser = listChore(choreList);
        return new CommandResult(feedbackToUser);
    }

}
