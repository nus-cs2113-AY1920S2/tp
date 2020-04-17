package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Abstract class to represent user's command.
 */
public abstract class Command {
    
    public static String actionType; //add, delete, list
    public static String objectType; //ingredient, recipe, chore
    public static String objectVariables;
    
    public Command() {
    }

    public String getObjectVariables() {
        return objectVariables;
    }
    
    /**
     * Set the Object Variables for Command.
     *
     * @param attribute the values for the variables, can be recipe or ingredient names
     */
    public void setObjectVariables(String attribute) {
        objectVariables = attribute;
    }
    
    /**
     * Set the Action Type for the Command.
     */
    
    public void setActionType(String command) {
        actionType = command;
    }
    
    /**
     * Set the Object Type for the Command.
     *
     * @param type the name of the object type
     */
    
    public void setObjectType(String type) {
        objectType = type;
    }
    
    /**
     * Runs the command given by user.
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return cmdResult response given to user after successful execution.
     * @throws KitchenHelperException if the command is invalid.
     */
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) throws KitchenHelperException, IOException {
        String feedbackToUser = "";
        CommandResult cmdResult = new CommandResult(feedbackToUser);
        return cmdResult;
    }
}
