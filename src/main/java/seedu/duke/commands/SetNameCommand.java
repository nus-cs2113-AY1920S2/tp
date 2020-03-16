package seedu.duke.commands;

import seedu.duke.Profile;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class SetNameCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String name;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetNameCommand(String command, String description) throws InvalidFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.name = descriptionArray[0];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setName(this.name);
        System.out.println(String.format("Your username has been changed to %s", profile.getName()));
    }
}
