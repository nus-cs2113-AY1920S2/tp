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
 * Search for chore in the chore list.
 */
public class SearchChoreCommand extends Command {
    
    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "searchchore";
    public static final String COMMAND_DESC = "Find chores in the chore list using a keyword.";
    public static final String COMMAND_PARAMETER = "<KEYWORD>";
    public static final String COMMAND_EXAMPLE = "Example: searchchore groceries";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    private static final String SEARCH_TYPE = "chores";
    
    private String keyword;
    
    /**
     * Constructor for Search Chore Command.
     * @param keyword the word to search.
     */
    public SearchChoreCommand(String keyword) {
        this.keyword = keyword.trim().toLowerCase();
    }
    
    /**
     * {@inheritDoc}
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the list of matching chores.
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
        ArrayList<Chore> findChoreList = new ArrayList<>();
        ArrayList<Integer> choreIndex = new ArrayList<>();
        int currIndex = 1;
        for (Chore chore : choreList) {
            if (chore.toString().toLowerCase().contains(keyword)) {
                findChoreList.add(chore);
                choreIndex.add(currIndex);
            }
            currIndex++;
        }
        
        if (findChoreList.isEmpty()) {
            kitchenLogs.info(String.format(Messages.MESSAGE_SEARCH_LOG_INFO_EMPTY, SEARCH_TYPE));
            return new CommandResult(String.format(Messages.MESSAGE_SEARCH_EMPTY_LIST, SEARCH_TYPE));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Messages.MESSAGE_SEARCH_NON_EMPTY_LIST, SEARCH_TYPE))
                .append(Ui.LS);
        assert findChoreList.size() > 0;
        kitchenLogs.info(String.format(Messages.MESSAGE_SEARCH_LOG_INFO_FOUND, SEARCH_TYPE));
        for (int i = 0; i < findChoreList.size(); ++i) {
            if (i == findChoreList.size() - 1) {
                sb.append(String.format(Messages.NUMBER_FORMAT, i + 1))
                        .append(findChoreList.get(i).toString())
                        .append(String.format(Messages.SEARCH_INDEX, choreIndex.get(i)));
                break;
            }
            sb.append(String.format(Messages.NUMBER_FORMAT, i + 1))
                    .append(findChoreList.get(i).toString())
                    .append(String.format(Messages.SEARCH_INDEX, choreIndex.get(i)))
                    .append(Ui.LS);
        }
        return new CommandResult(sb.toString());
        
    }
}
