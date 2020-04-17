package seedu.kitchenhelper.command;

import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;

/**
 * Shows the user how to execute different commands supported by this program.
 */
public class HelpCommand extends Command {
    
    public static final String COMMAND_WORD = "help";
    public static final String LS_LS = Ui.LS + Ui.LS;
    public static final String DLS = Ui.DIVIDER + Ui.LS;
    public static final String INGREDIENT_LOGO = DLS + "Ingredient" + Ui.LS + DLS;
    public static final String RECIPE_LOGO = DLS + "Recipe" + Ui.LS + DLS;
    public static final String CHORE_LOGO = DLS + "Chore" + Ui.LS + DLS;
    public static final String COMMON_LOGO = DLS + "Common" + Ui.LS + DLS;
    public static final String COMMAND_DESC = "Shows the program command line interface instructions.";
    public static final String MESSAGE_USAGE =
            String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String.format("Example: %s", COMMAND_WORD);
    
    
    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the success message of printing help.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        return new CommandResult(
                DLS + COMMON_LOGO
                + HelpCommand.MESSAGE_USAGE + LS_LS
                + ResetCommand.MESSAGE_USAGE + LS_LS
                + SaveStateCommand.MESSAGE_USAGE + LS_LS
                + DisplayExpenditureCommand.MESSAGE_USAGE + LS_LS
                + ExitCommand.MESSAGE_USAGE + LS_LS
                + INGREDIENT_LOGO
                + AddIngredientCommand.MESSAGE_USAGE + LS_LS
                + ListIngredientCommand.MESSAGE_USAGE + LS_LS
                + DeleteIngredientCommand.MESSAGE_USAGE + LS_LS
                + SearchIngredientCommand.MESSAGE_USAGE + LS_LS
                + RECIPE_LOGO
                + AddRecipeCommand.MESSAGE_USAGE + LS_LS
                + ListRecipeCommand.MESSAGE_USAGE + LS_LS
                + CookRecipeCommand.MESSAGE_USAGE + LS_LS
                + DeleteRecipeCommand.MESSAGE_USAGE + LS_LS
                + SearchRecipeCommand.MESSAGE_USAGE + LS_LS
                + CHORE_LOGO
                + AddChoreCommand.MESSAGE_USAGE + LS_LS
                + ListChoreCommand.MESSAGE_USAGE + LS_LS
                + DoneCommand.MESSAGE_USAGE + LS_LS
                + DeleteChoreCommand.MESSAGE_USAGE + LS_LS
                + SearchChoreCommand.MESSAGE_USAGE);
    }
}
