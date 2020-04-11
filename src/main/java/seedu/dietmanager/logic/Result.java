package seedu.dietmanager.logic;

/**
 * Result is the public class responsible for storing results derived from execution of the Command.
 */

public class Result {

    /**
     * The results derived from execution of a command.
     */

    private String commandResult;

    /**
     * Constructs the Result object.
     *
     * @param commandResult The results derived from execution of a command.
     */

    public Result(String commandResult) {
        this.commandResult = commandResult;
    }

    /**
     * Returns the results derived from the execution of a command.
     *
     * @return the results derived from the execution of a command.
     */

    public String showResult() {
        return this.commandResult;
    }

}
