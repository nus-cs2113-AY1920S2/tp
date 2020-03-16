package seedu.duke.commands;

import seedu.duke.Profile;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class SetProfileCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 6;
    private String name;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private double weightGoal;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetProfileCommand(String command, String description) throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.name = descriptionArray[0];
        this.age = Integer.parseInt(descriptionArray[1]);
        this.gender = descriptionArray[2];
        this.height = Double.parseDouble(descriptionArray[3]);
        this.weight = Double.parseDouble(descriptionArray[4]);
        this.weightGoal = Double.parseDouble(descriptionArray[5]);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setProfile(this.name, this.age, this.gender, this.height, this.weight, this.weightGoal);
        System.out.println("Profile updated successfully!");
    }
}
