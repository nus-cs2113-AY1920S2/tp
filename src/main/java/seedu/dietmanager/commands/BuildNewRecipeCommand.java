package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.RecipeManager;
import seedu.dietmanager.exceptions.NegativeNumberException;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class BuildNewRecipeCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;

    private boolean noDescription;
    private boolean isInvalidFormat;

    private double caloriesCap;
    private int maxFoodNum;

    /**
     * Constructs the command object.
     *
     * @param command the command prompt entered by the user.
     * @param description the description of the command.
     * @throws InvalidFormatException if the command doesn't contain correct number of parameters.
     */

    public BuildNewRecipeCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;
        this.isInvalidFormat = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);

            this.caloriesCap = Double.parseDouble(descriptionArray[0].trim());
            this.maxFoodNum = Integer.parseInt(descriptionArray[1].trim());

            if (caloriesCap <= 0 || maxFoodNum <= 0) {
                throw new NegativeNumberException();
            }

        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (NumberFormatException | NegativeNumberException e) {
            this.isInvalidFormat = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!noDescription && !isInvalidFormat) {
            RecipeManager manager = RecipeManager.getInstance();
            manager.customRecipe(caloriesCap,maxFoodNum);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (isInvalidFormat) {
            this.result = "You have given wrong format for parameters!!!";
        } else {
            this.result = RecipeManager.getInstance().getRecipe();
        }
    }
}
