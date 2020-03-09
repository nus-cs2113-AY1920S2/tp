package seedu.nuke.parser;

import seedu.nuke.command.Command;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.data.ModuleManager;

public class Parser {
    public static final String COMMAND_SPLITTER = " ";
    public static final int COMMAND_WORD_INDEX = 0;
    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * split the user input, command word and the description
     * commandWord stores the whole user input
     * commandWordFirstPart [0] stores the Command Word
     * description stores additional information
     */
    public Command parseCommand(ModuleManager moduleManager, String userInput) {
        final String commandWord = userInput;
        final String []commandWordFirstPart = commandWord.split(COMMAND_SPLITTER);
        /** further split the user input, get the secondary part, the description */
        final String commandWordDescription = commandWord.substring(commandWordFirstPart[COMMAND_WORD_INDEX].length());
        //operates according to different command word
        return getCommand(commandWordFirstPart);
    }

    /**
     * @param commandWordFirstPart, commandWordDescription the input String spited parts
     * @return parsed command
     */
    private Command getCommand(String[] commandWordFirstPart) {
        switch (commandWordFirstPart[COMMAND_WORD_INDEX]){
        //exit
        case ExitCommand.COMMAND_WORD:
        default:
            return new ExitCommand();
        }
    }

}
