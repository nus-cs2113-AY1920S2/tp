package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;

public class DeleteWeightCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weightDeleted;
    private String weightDeletedDay;
    private int index;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public DeleteWeightCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException, IndexOutOfBoundsException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.index = Integer.parseInt(descriptionArray[0]) - 1;
    }

    @Override
    public void execute(Profile profile, UI ui) {
        weightDeleted = profile.getWeightProgress().get(index);
        weightDeletedDay = profile.getWeightProgressDays().get(index);
        profile.getWeightProgress().remove(index);
        profile.getWeightProgressDays().remove(index);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = weightDeleted + "kg " + weightDeletedDay + MessageBank.WEIGHT_DELETED_MESSAGE;
    }

}
