package exception.command;

import exception.CustomException;

import static ui.Constants.HELP_DESCRIPTION_5;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't follow the format required to create a deadline task.
 */
public class DeadlineCreationFormatNotFollowedException extends CustomException {

    public static final String DEADLINE_CREATION_FORMAT_NOT_FOLLOWED = "Please follow the correct format to add a "
            + "new deadline" + System.lineSeparator() + HELP_DESCRIPTION_5;

    public DeadlineCreationFormatNotFollowedException() {
        super(DEADLINE_CREATION_FORMAT_NOT_FOLLOWED);
    }
}