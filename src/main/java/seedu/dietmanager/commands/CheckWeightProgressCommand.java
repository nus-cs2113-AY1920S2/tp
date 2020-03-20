package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;

public class CheckWeightProgressCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String profileName;
    private ArrayList<Double> weightRecord = new ArrayList<>();

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public CheckWeightProgressCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.profileName = descriptionArray[0];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        weightRecord = profile.getWeightProgress();
        System.out.println(String.format("Here is your weight changes record:"));
        for (double weight : weightRecord) {
            ui.showCommandMessage(String.valueOf(weight));
        }
    }
}
