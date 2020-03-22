package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class WeightUpdateCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 2;
    private double weightUpdate;
    private String day;

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
        this.day = descriptionArray[1];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.addWeightProgress(weightUpdate, day);
        saveResult(profile);
    }
    
    @Override
    public void saveResult(Profile profile) {
        this.result = MessageBank.WEIGHT_UPDATE_MESSAGE + String.format("%.2f.",weightUpdate);
    }
}
