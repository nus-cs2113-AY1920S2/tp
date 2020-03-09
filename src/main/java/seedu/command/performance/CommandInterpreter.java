package seedu.command.performance;

import seedu.exception.DukeException;

import static seedu.parser.Parser.getCommandWord;

public class CommandInterpreter {

    /**
     * Execute the command from userInput.
     *
     * @param userInput The userInput from the Ui.
     * @return The command object.
     * @throws DukeException If the command is undefined.
     */
    public static Command executeCommand(String userInput) throws DukeException {
        String commandWord = getCommandWord(userInput);
        switch (commandWord) {
        //case "addEvent":
        //  create new EventObject
        default:
            throw new DukeException("UNKNOWN COMMAND");
        }
    }

}
