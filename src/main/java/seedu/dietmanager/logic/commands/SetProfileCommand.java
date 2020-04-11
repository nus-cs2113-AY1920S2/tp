package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidAgeException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.commons.exceptions.InvalidGenderException;
import seedu.dietmanager.commons.exceptions.InvalidHeightException;
import seedu.dietmanager.commons.exceptions.InvalidNameException;
import seedu.dietmanager.commons.exceptions.InvalidWeightException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.AgeParser;
import seedu.dietmanager.logic.parser.DescriptionParser;
import seedu.dietmanager.logic.parser.GenderParser;
import seedu.dietmanager.logic.parser.HeightParser;
import seedu.dietmanager.logic.parser.NameParser;
import seedu.dietmanager.logic.parser.WeightParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetProfileCommand extends Command {

    private String name;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private double weightGoal;
    private boolean isValidCommand;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetProfileCommand(String command, String description) {
        super(command);
        this.isValidCommand = true;
        try {
            String[] descriptionArray = DescriptionParser.parseDescription(description, 6);
            this.name = NameParser.parseName(descriptionArray[0]);
            this.age = AgeParser.parseAge(descriptionArray[1]);
            this.gender = GenderParser.parseGender(descriptionArray[2]);
            this.height = HeightParser.parseHeight(descriptionArray[3]);
            this.weight = WeightParser.parseWeight(descriptionArray[4]);
            this.weightGoal = WeightParser.parseWeight(descriptionArray[5]);
        } catch (NullPointerException | InvalidHeightException | InvalidWeightException | InvalidAgeException
                | InvalidNameException | InvalidGenderException | InvalidFormatException e) {
            this.isValidCommand = false;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        if (this.isValidCommand) {
            profile.setProfile(this.name, this.age, this.gender, this.height, this.weight, this.weightGoal);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.isValidCommand) {
            this.resultString = MessageBank.PROFILE_UPDATE_MESSAGE;
        } else {
            this.resultString = MessageBank.INVALID_FORMAT_MESSAGE;
        }
        return new Result(this.resultString);
    }
}
