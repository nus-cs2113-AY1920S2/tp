package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji

/**
 * Exception is thrown if the user doesn't follow the format to create a schedulable task.
 */
public class SchedulableTaskFormatNotFollowedException extends CustomException {

    public static final String PLEASE_FOLLOW_THE_FORMAT_SPECIFIED = "Please follow the format specified";

    public SchedulableTaskFormatNotFollowedException() {
        super(PLEASE_FOLLOW_THE_FORMAT_SPECIFIED);
    }
}