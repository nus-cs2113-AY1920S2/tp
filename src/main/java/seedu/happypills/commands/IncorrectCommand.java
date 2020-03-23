package seedu.happypills.commands;

import seedu.happypills.data.PatientMap;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(PatientMap patients) {
        return feedbackToUser;
    }

}