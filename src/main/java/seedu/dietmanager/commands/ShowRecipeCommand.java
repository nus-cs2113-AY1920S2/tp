package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.RecipeManager;
import seedu.dietmanager.ui.UI;

public class ShowRecipeCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 0;

    public ShowRecipeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = RecipeManager.getInstance().getRecipe();
    }
}
