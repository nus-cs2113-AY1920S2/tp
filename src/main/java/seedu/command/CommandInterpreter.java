package seedu.command;

import seedu.exception.DukeException;

import static seedu.parser.Parser.getCommandWord;

public class CommandInterpreter {

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
