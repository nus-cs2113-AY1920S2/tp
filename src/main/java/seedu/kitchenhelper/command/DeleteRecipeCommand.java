package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteRecipeCommand extends Command {
    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "deleterecipe";
    public static final String COMMAND_PARAMETER = "/n RECIPENAME OR /i RECIPEINDEX";
    public static final String COMMAND_USAGE = "Usage: deleterecipe /n RECIPENAME OR deleterecipe /i RECIPEINDEX";
    public static final String COMMAND_DESC = "Delete the recipe. ";
    public static final String COMMAND_EXAMPLE = "Example: deleterecipe /n Chicken Salad OR deleterecipe /i 1";
    public static final String COMMAND_FORMAT = String.format("%s\n%s\n%s", COMMAND_DESC, COMMAND_USAGE,
                                                    COMMAND_EXAMPLE);
    public static final String COMMAND_SUCCESS = "%s has been deleted";
    public static final String COMMAND_FAILURE = "This recipe does not exist! Please type in a correct "
                                                    + "recipe name/index.";
    public static final String LOG_INFO = "A recipe has been deleted";
    private static final String OBJECT_TYPE = "recipe";
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    private Integer recipeIndex;

    /**
     * Constructor for Delete Recipe Command.
     *
     * @param recipeName name of the recipe to be deleted
     *
     */
    public DeleteRecipeCommand(String recipeName) {
        setActionType(COMMAND_WORD);
        setObjectType(OBJECT_TYPE);
        setObjectVariables(recipeName);
        this.recipeIndex = null;
    }

    /**
     * Constructor for Delete Recipe Command.
     *
     * @param recipeIndex index of the recipe to be deleted
     *
     */
    public DeleteRecipeCommand(Integer recipeIndex) {
        setActionType(COMMAND_WORD);
        setObjectType(OBJECT_TYPE);
        this.recipeIndex = recipeIndex;
    }

    /**
     * Getting the index of the recipe to be deleted.
     *
     * @param recipeName the name of the recipe to be deleted
     * @param recipeList the list of recipe
     * @return
     */
    public int getRecipeIndex(String recipeName, ArrayList<Recipe> recipeList) {
        for (Recipe recipe : recipeList) {
            if (recipe.getRecipeName().equalsIgnoreCase(recipeName)) {
                int index = recipeList.indexOf(recipe);
                return index;
            }
        }
        return -1;
    }

    /**
     * Delete the recipe for the recipe list.
     *
     * @param recipeList the list of recipe
     */
    public String deleteRecipe(ArrayList<Recipe> recipeList, Integer recipeIndex) {
        String feedbackToUser;
        if (recipeIndex > -1 && recipeIndex < recipeList.size()) {
            assert recipeList.size() > 0;
            kitchenLogs.log(Level.INFO, LOG_INFO);
            String recipeName = recipeList.get(recipeIndex).getRecipeName();
            recipeList.remove(recipeList.get(recipeIndex));
            Storage.saveRecipeData(recipeList);
            feedbackToUser = String.format(COMMAND_SUCCESS, recipeName);
        } else {
            feedbackToUser = COMMAND_FAILURE;
        }
        return feedbackToUser;
    }

    /**
     * Delete the recipe by index.
     *
     * @param recipeList the list of recipe
     */
    public String deleteRecipeByIndex(ArrayList<Recipe> recipeList) {
        int index = this.recipeIndex;
        String feedbackToUser = deleteRecipe(recipeList, index);
        return feedbackToUser;
    }

    /**
     * Delete the recipe by name.
     *
     * @param recipeList the list of recipe
     */
    public String deleteRecipeByName(ArrayList<Recipe> recipeList) {
        String recipeName = this.objectVariables;
        int recipeIndex = getRecipeIndex(recipeName, recipeList);
        String feedbackToUser = deleteRecipe(recipeList, recipeIndex);
        return feedbackToUser;
    }

    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the execution of the deletion of ingredients or tasks.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                  ArrayList<Chore> choreList) throws KitchenHelperException {
        String feedbackToUser;
        if (this.recipeIndex == null) {
            feedbackToUser = deleteRecipeByName(recipeList);
        } else {
            feedbackToUser = deleteRecipeByIndex(recipeList);
        }
        CommandResult cmdResult = new CommandResult(feedbackToUser);
        return cmdResult;
    }
}
