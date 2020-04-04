package seedu.dietmanager.logic.commands;

import seedu.dietmanager.model.Profile;
import seedu.dietmanager.model.RecipeManager;
import seedu.dietmanager.commons.exceptions.NegativeNumberException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.parser.Parser;
import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.ui.UI;

public class BuildNewRecipeCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;

    private boolean noDescription;
    private boolean isInvalidFormat;
    private boolean maxNumOverflow;

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
            maxNumOverflow = manager.customRecipe(caloriesCap,maxFoodNum);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = "";

        if (noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (isInvalidFormat) {
            this.result = "You have given wrong format for parameters!!!";
        } else {
            if (maxNumOverflow) {
                this.result = "We support at most 4 kinds of food in a meal, "
                        + "otherwise it's not good for your health!\n\n";
            }
            this.result += RecipeManager.getInstance().getRecipe();
        }
    }
}
