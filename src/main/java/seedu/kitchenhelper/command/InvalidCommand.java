package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;

import java.util.ArrayList;

/**
 * Represent an invalid command. Upon execution, produces some feedback to the user.
 */
public class InvalidCommand extends Command {
    
    public static final String MESSAGE_INVALID = "Invalid Command Format!";
    
    private final String invalidFeedback;
    
    /**
     * Constructor for InvalidCommand.
     */
    public InvalidCommand() {
        this.invalidFeedback = MESSAGE_INVALID;
    }
    
    /**
     * Constructor for InvalidCommand.
     *
     * @param invalidFeedback feedback for invalid command message.
     */
    public InvalidCommand(String invalidFeedback) {
        this.invalidFeedback = invalidFeedback;
    }

    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the invalid message.
     */
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        return new CommandResult(invalidFeedback);
    }
}
