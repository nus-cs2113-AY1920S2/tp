package exception.command;

import exception.CustomException;

import static ui.Constants.HELP_DESCRIPTION_3;

/**
 * Exception is thrown if the user doesn't follow the format required to create an event task.
 */
//@@author GanapathySanathBalaji
public class EventCreationFormatNotFollowedException extends CustomException {

    public static final String EVENT_CREATION_FORMAT_NOT_FOLLOWED = "Please follow the correct format to add a new "
            + "event" + System.lineSeparator() + HELP_DESCRIPTION_3;

    public EventCreationFormatNotFollowedException() {
        super(EVENT_CREATION_FORMAT_NOT_FOLLOWED);
    }
}