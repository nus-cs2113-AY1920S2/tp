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
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public WeightUpdateCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.weightUpdate = Double.parseDouble(descriptionArray[0]);
            this.day = descriptionArray[1];
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            profile.addWeightProgress(weightUpdate, day);
        }
        saveResult(profile);
    }
    
    @Override
    public void saveResult(Profile profile) {
        if (!this.noDescription) {
            this.result = MessageBank.WEIGHT_UPDATE_MESSAGE + String.format("%.2f.",weightUpdate);
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
