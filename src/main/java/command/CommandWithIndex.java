package command;

import exception.command.IndexNotIntegerException;
import exception.command.MissingIndexException;
import task.TaskList;
import ui.Ui;

//@@author NizarMohd

/**
 * Functions as a parent class for commands which takes in a parameter for the index of the task.
 */
public class CommandWithIndex extends Command {


    public static final String DELETE_COMMAND = "delete";
    public static final String EDIT_COMMAND = "edit";
    public static final String DONE_COMMAND = "done";
    public static final String INDEX_TO_DELETE_NOT_MENTIONED = "Index to delete not mentioned";
    public static final String INDEX_TO_EDIT_NOT_MENTIONED = "Index to edit not mentioned";
    public static final String INDEX_TO_BE_MARKED_AS_DONE_NOT_MENTIONED = "Index to be marked as done not mentioned";
    public static final int COMMAND_TYPE = 0;
    public static final int INDEX = 1;
    /** Denotes the index at which the executeCommand method will operate at. */
    private static int index;

    /**
     * Constructor for commands with index, or CommandWithIndex object.
     * Creates a new CommandWithIndex object if the correct command is used.
     *
     * @param commandSplit Contains information with regards to the index to be operated at.
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @throws Exception If the index specified is invalid/ The wrong format is used for the command.
     */
    public CommandWithIndex(String[] commandSplit, boolean isOneWordCommand) throws Exception {
        if (isOneWordCommand) {
            String errorMessage = getErrorMessage(commandSplit[COMMAND_TYPE]);
            throw new MissingIndexException(errorMessage);
        }
        try {
            setIndex(Integer.parseInt(commandSplit[INDEX]));
        } catch (NumberFormatException e) {
            throw new IndexNotIntegerException();
        }
    }

    /**
     * This method gets the index where the process should be executed.
     * @return integer value, which denotes the index mentioned above.
     */

    public static int getIndex() {
        return index;
    }

    /**
     * This method sets the value of the index attribute in CommandWithIndex object.
     * @param index this is the value of the index that constructor will set.
     */
    public static void setIndex(int index) {
        CommandWithIndex.index = index;
    }

    /**
     * This method assigns the error message based on command used.
     * @param commandType this is the type of command entered by user.
     * @return String value of the error message depending on command type.
     */
    public String getErrorMessage(String commandType) {
        switch (commandType) {
        case DELETE_COMMAND:
            return INDEX_TO_DELETE_NOT_MENTIONED;
        case EDIT_COMMAND:
            return INDEX_TO_EDIT_NOT_MENTIONED;
        case DONE_COMMAND:
            return INDEX_TO_BE_MARKED_AS_DONE_NOT_MENTIONED;
        default:
            return null;
        }
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui) throws Exception {

    }
}
