package seedu.duke.commands;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
//@@author trishaangelica, jiajuinphoon, Shannonwje, kokjoon97, JLoh579
public class IncorrectCommand extends Command {

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public void execute() {
    }

}
//@@author
