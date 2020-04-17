package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Adds recipe to the list of recipes.
 */
public class AddRecipeCommand extends Command {

    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "addrecipe";
    public static final String COMMAND_DESC = "Adds a recipe to the recipe list.";
    public static final String COMMAND_PARAMETER
            = "/n RECIPENAME /i INGRNAME:QUANTITY:CATEGORY <optional: , INGRNAME:QUANTITY:CATEGORY>";
    public static final String COMMAND_EXAMPLE
            = "Example: addrecipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public final String logAddRecipe = "A new recipe has been added";
    public HashMap<String[], Integer> parsedIngr;

    /**
     * Set the attributes of the Command class.
     *
     * @param rawString     full user input string.
     * @param ingrAndQty    a hashmap of ingredient with [ingredientName, ingredientCategory] as key
     *                      and ingredientQuantity as value.
     */
    public void setAttributesOfCmd(String rawString, HashMap<String[], Integer> ingrAndQty) {
        setObjectVariables(rawString);
        setAction();
        this.parsedIngr = ingrAndQty;
    }

    /**
     * Sets the string without the keyword to be the values of the reicpe object.
     *
     * @param rawString string that has removed the keyword.
     * */
    public void setObjectVariables(String rawString) {
        objectVariables = rawString;
    }

    /**
     * Sets the object's action to add recipe.
     */
    public void setAction() {
        actionType = COMMAND_WORD;
    }

    /**
     * The main utility function to add recipe.
     * @param attributes     list of ingredients that have not been parsed.
     * @param recipeList     list of recipes.
     * @return the success message of adding inventory.
     */
    public String addRecipe(String attributes, ArrayList<Recipe> recipeList) throws KitchenHelperException {
        Recipe freshRecipe = new Recipe();
        freshRecipe.setRecipeName(attributes);
        if (checkIfRecipeExist(freshRecipe.getRecipeName(), recipeList)) {
            throw new KitchenHelperException("There is an existing recipe!");
        }
        freshRecipe.addIngredientsToRecipe(parsedIngr);
        recipeList.add(freshRecipe);
        Storage.saveRecipeData(recipeList);
        assert freshRecipe.recipeName.length() > 0;
        assert recipeList.size() > 0;
        kitchenLogs.info(logAddRecipe);
        return freshRecipe.recipeName + " Recipe has been created with "
                + freshRecipe.recipeIngrQty + " ingredients inside.";
    }

    /**
     * Checks for existing recipe with the same name.
     * @param newRecipeName The name of the new recipe.
     * @param recipeList    The list of recipes.
     * @return true when a recipe with the same name is found,
     *          false otherwise.
     */
    public Boolean checkIfRecipeExist(String newRecipeName, ArrayList<Recipe> recipeList) {
        boolean isExist = false;
        for (Recipe recipe : recipeList) {
            if (recipe.getRecipeName().equalsIgnoreCase(newRecipeName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }


    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the success message of adding recipe.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) throws KitchenHelperException {
        try {
            String message = addRecipe(this.objectVariables, recipeList);
            return new CommandResult(message);
        } catch (KitchenHelperException e) {
            return new CommandResult("There is an existing recipe with the same name!");
        }
    }
}
