package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.exceptions.InvalidGenderException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class SetProfileCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 6;
    private String name;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private double weightGoal;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetProfileCommand(String command, String description) throws InvalidFormatException,
            NumberFormatException, InvalidGenderException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.name = descriptionArray[0].trim();
            this.age = Integer.parseInt(descriptionArray[1].trim());
            this.gender = Parser.parseGender(descriptionArray[2].trim());
            this.height = Double.parseDouble(descriptionArray[3].trim());
            this.weight = Double.parseDouble(descriptionArray[4].trim());
            this.weightGoal = Double.parseDouble(descriptionArray[5].trim());
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            profile.setProfile(this.name, this.age, this.gender, this.height, this.weight, this.weightGoal);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else {
            this.result = MessageBank.PROFILE_UPDATE_MESSAGE;
        }
    }
}
