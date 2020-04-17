package seedu.kitchenhelper.command;

import seedu.kitchenhelper.common.Messages;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Search for recipe name in the recipe list.
 */
public class SearchRecipeCommand extends Command {
    
    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "searchrecipe";
    public static final String COMMAND_DESC = "Find common recipe name in the recipe list using a keyword.";
    public static final String COMMAND_PARAMETER = "<KEYWORD>";
    public static final String COMMAND_EXAMPLE = "Example: searchrecipe chicken stew";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    private static final String SEARCH_TYPE = "recipes";

    private String keyword;
    
    /**
     * Constructor for Search Recipe Command.
     * @param keyword the word to search.
     */
    public SearchRecipeCommand(String keyword) {
        this.keyword = keyword.trim().toLowerCase();
    }
    
    /**
     * {@inheritDoc}
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the list of matching recipe.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
    
        try {
            if (this.keyword.equals(Messages.EMPTY_STRING)) {
                throw new KitchenHelperException();
            }
        } catch (KitchenHelperException khe) {
            return new CommandResult(Messages.MESSAGE_SEARCH_EMPTY_STRING);
        }
        
        kitchenLogs.info(String.format(Messages.MESSAGE_SEARCH_LOG_INFO, SEARCH_TYPE));
        ArrayList<Recipe> findRecipeList = new ArrayList<>();
        ArrayList<Integer> recipeIndex = new ArrayList<>();
        int currIndex = 1;
        for (Recipe recipe : recipeList) {
            if (recipe.getRecipeName().toLowerCase().contains(keyword)) {
                findRecipeList.add(recipe);
                recipeIndex.add(currIndex);
            }
            currIndex++;
        }
        
        if (findRecipeList.isEmpty()) {
            kitchenLogs.info(String.format(Messages.MESSAGE_SEARCH_LOG_INFO_EMPTY, SEARCH_TYPE));
            return new CommandResult(String.format(Messages.MESSAGE_SEARCH_EMPTY_LIST, SEARCH_TYPE));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Messages.MESSAGE_SEARCH_NON_EMPTY_LIST, SEARCH_TYPE))
                .append(Ui.LS);
        assert findRecipeList.size() > 0;
        kitchenLogs.info(String.format(Messages.MESSAGE_SEARCH_LOG_INFO_FOUND, SEARCH_TYPE));
        for (int i = 0; i < findRecipeList.size(); ++i) {
            if (i == findRecipeList.size() - 1) {
                sb.append(String.format(Messages.NUMBER_FORMAT, i + 1))
                        .append(findRecipeList.get(i).getRecipeName())
                        .append(String.format(Messages.SEARCH_INDEX, recipeIndex.get(i)));
                break;
            }
            sb.append(String.format(Messages.NUMBER_FORMAT, i + 1))
                    .append(findRecipeList.get(i).getRecipeName())
                    .append(String.format(Messages.SEARCH_INDEX, recipeIndex.get(i)))
                    .append(Ui.LS);
        }
        return new CommandResult(sb.toString());
    }
}
