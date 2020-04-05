package seedu.dietmanager.logic.commands;

import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.model.RecipeManager;
import seedu.dietmanager.ui.UI;

public class ShowRecipeCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 0;

    public ShowRecipeCommand(String command) {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        this.resultString = RecipeManager.getInstance().getRecipe();
        return new Result(this.resultString);
    }
}
