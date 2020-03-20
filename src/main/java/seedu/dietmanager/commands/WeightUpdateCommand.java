package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.UI;

public class WeightUpdateCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weightUpdate;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public WeightUpdateCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.weightUpdate = Double.parseDouble(descriptionArray[0]);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.addWeightProgress(weightUpdate);
        System.out.println(String.format("Your weight changes has been stored. Current weight is %.2f", weightUpdate));
    }
}
