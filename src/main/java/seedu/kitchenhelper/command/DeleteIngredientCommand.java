package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.object.Expenditure;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteIngredientCommand extends Command {
    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "deleteingredient";
    public static final String COMMAND_USAGE = "Usage: deleteingredient /i INGREDIENTINDEX [/q QUANTITY]";
    public static final String COMMAND_PARAMETER = "/i INGREDIENTINDEX [/q QUANTITY]";
    public static final String COMMAND_DESC = "Deletes an ingredient.";
    public static final String COMMAND_EXAMPLE = "Example: deleteingredient /i 1 /q 2";
    public static final String COMMAND_FORMAT = String.format("%s\n%s\n%s", COMMAND_DESC, COMMAND_USAGE,
                                                            COMMAND_EXAMPLE);
    public static final String COMMAND_SUCCESS = "%s has been deleted.";
    public static final String COMMAND_FAILURE = "This ingredient does not exist! Please type in a correct "
                                                    + "ingredient index.";
    public static final String COMMAND_SUCCESS_QUANTITY = "The quantity of %s has been changed!";
    public static final String COMMAND_SUCCESS_ZERO_QUANTITY = "\nThis ingredient has a quantity of 0 after "
                                                                + "deduction, so it has been deleted.";
    public static final String COMMAND_FAILURE_QUANTITY = "Please enter a valid quantity to delete!\nCurrently:"
                                                            + "\n%s : %d";
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String LOG_INFO = "An ingredient has been deleted";
    private static final String OBJECT_TYPE = "ingredient";
    private Integer quantity;
    private Integer ingredientIndex;

    /**
     * Constructor for Delete Ingredient Command.
     *
     * @param ingredientIndex index of the ingredient to be deleted
     * @param quantity number of serving of ingredient to be deleted
     */

    public DeleteIngredientCommand(Integer ingredientIndex, Integer quantity) {
        setActionType(COMMAND_WORD);
        setObjectType(OBJECT_TYPE);
        this.quantity = quantity;
        this.ingredientIndex = ingredientIndex;
    }

    /**
     * Delete the ingredient by index.
     *
     * @param ingredientsList the list of ingredients
     * @return feedback to user
     */

    public String deleteIngredientByIndex(ArrayList<Ingredient> ingredientsList) {
        String feedbackToUser = "";
        int ingredientIndex = this.ingredientIndex;
        if (ingredientIndex > -1 && ingredientIndex < ingredientsList.size()) {
            assert ingredientIndex >= 0;
            Ingredient ingredientToDelete = ingredientsList.get(ingredientIndex);
            //Delete the entire ingredient when quantity is equals to zero too
            if (quantity != null) {
                feedbackToUser = deleteQuantity(ingredientToDelete);
            }
            int ingredientQuantity = ingredientToDelete.getQuantity();
            if (ingredientQuantity == 0 || quantity == null) {
                feedbackToUser += deleteIngredient(ingredientToDelete, ingredientsList);
            }
            Storage.saveIngredientData(ingredientsList);
        } else {
            feedbackToUser = COMMAND_FAILURE;
        }
        return feedbackToUser;
    }

    /**
     * Delete the ingredient from the ingredient list.
     *
     * @param ingredientToDelete Ingredient to be deleted
     * @param ingredientsList the list of ingredients
     * @return feedback to user
     */

    public String deleteIngredient(Ingredient ingredientToDelete, ArrayList<Ingredient> ingredientsList) {
        kitchenLogs.log(Level.INFO, LOG_INFO);
        String feedbackToUser;
        String ingredientName = ingredientToDelete.getIngredientName();
        int ingredientQuantity = ingredientToDelete.getQuantity();
        if (ingredientQuantity == 0 && quantity != null) {
            feedbackToUser = String.format(COMMAND_SUCCESS_ZERO_QUANTITY, ingredientName);
        } else {
            feedbackToUser = String.format(COMMAND_SUCCESS, ingredientName);
            Expenditure.getInstance().editExpenditure(ingredientToDelete, quantity);
        }
        ingredientsList.remove(ingredientToDelete);
        return feedbackToUser;
    }

    /**
     * Deletes the quantity specified by the user from the ingredient.
     *
     * @param ingredientToDelete Ingredient to be deleted
     * @return feedback to user
     */

    public String deleteQuantity(Ingredient ingredientToDelete) {
        String feedbackToUser;
        String ingredientName = ingredientToDelete.getIngredientName();
        int ingredientQuantity = ingredientToDelete.getQuantity();
        if (quantity > 0) {
            int newQuantity = ingredientQuantity - quantity;
            feedbackToUser = updateNewQuantity(newQuantity, ingredientToDelete);
        } else {
            feedbackToUser = String.format(COMMAND_FAILURE_QUANTITY, ingredientName, ingredientQuantity);
        }
        return feedbackToUser;
    }

    /**
     * Update the quantity of an ingredient the ingredient list.
     *
     * @param newQuantity  the new quantity for the ingredient
     * @param ingredientToDelete the ingredient to be deleted
     * @return feedback to user
     */

    public String updateNewQuantity(int newQuantity, Ingredient ingredientToDelete) {
        String feedbackToUser;
        String ingredientName = ingredientToDelete.getIngredientName();
        int ingredientQuantity = ingredientToDelete.getQuantity();

        if (newQuantity < 0) {
            feedbackToUser = String.format(COMMAND_FAILURE_QUANTITY, ingredientName, ingredientQuantity);
            assert ingredientQuantity != newQuantity;
        } else {
            Expenditure.getInstance().editExpenditure(ingredientToDelete, quantity);
            ingredientToDelete.setQuantity(newQuantity);
            feedbackToUser = String.format(COMMAND_SUCCESS_QUANTITY, ingredientName);
            assert ingredientToDelete.getQuantity() == newQuantity;
        }
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
                                  ArrayList<Chore> choreList) {
        String feedbackToUser;
        feedbackToUser = deleteIngredientByIndex(ingredientList);
        CommandResult cmdResult = new CommandResult(feedbackToUser);
        return cmdResult;
    }
}
