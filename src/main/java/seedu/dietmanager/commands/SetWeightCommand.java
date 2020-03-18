package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.UI;

public class SetWeightCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weight;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetWeightCommand(String command, String description) throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.weight = Double.parseDouble(descriptionArray[0]);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setWeight(this.weight);
        System.out.println(String.format("Your height has been changed to %.2f", profile.getWeight()));
    }
}
