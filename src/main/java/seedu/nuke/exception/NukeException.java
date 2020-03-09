package seedu.nuke.exception;

/**
 * The parent exception class that handles the general exception.
 *
 * @author A11riseforme
 */
public class NukeException extends Exception {

    /**
     * Default constructor.
     */
    public NukeException() {
        super();
    }

    /**
     * Constructor with one argument as the error message.
     *
     * @param errorMsg the message to be shown when exception is raised.
     */
    public NukeException(String errorMsg) {
        super(errorMsg);
    }

}