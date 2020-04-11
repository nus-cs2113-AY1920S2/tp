package exception.command;

import exception.CustomException;

//@@author GanapathySanathBalaji
/**
 * Exception is thrown if the user didn't follow the correct format to schedule tasks.
 */
public class MisuseOfScheduleCommandException extends CustomException {

    public static final String INVALID_SCHEDULE_COMMAND = "Wrong command to create schedule events"
            + " (Should be :schedule <number of tasks> )";

    public MisuseOfScheduleCommandException() {
        super(INVALID_SCHEDULE_COMMAND);
    }
}
