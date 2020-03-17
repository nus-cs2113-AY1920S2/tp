package seedu.nuke.command.promptCommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.common.DataType;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_LIST_NUMBER_NOT_FOUND;
import static seedu.nuke.util.Message.*;

public class ListNumberPrompt extends Command {
    public static final Pattern INDICES_FORMAT = Pattern.compile("(?<indices>(?:\\s*\\d+)+\\s*)");

    private ArrayList<Integer> indices;

    public ListNumberPrompt(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    @SuppressWarnings("unchecked")
    private CommandResult executePromptConfirmation() {
        DataType dataType = Executor.getDataType();
        switch (dataType) {
            case MODULE: {
                ArrayList<Module> filteredList = (ArrayList<Module>) Executor.getFilteredList();
                return new CommandResult(messageConfirmDeleteModule(filteredList, indices));
            }
            case CATEGORY: {
                ArrayList<Category> filteredList = (ArrayList<Category>) Executor.getFilteredList();
                return new CommandResult(messageConfirmDeleteCategory(filteredList, indices));
            }
            case TASK: {
                ArrayList<Task> filteredList = (ArrayList<Task>) Executor.getFilteredList();
                return new CommandResult(messageConfirmDeleteTask(filteredList, indices));
            }
            default:
                return new CommandResult("Error in displaying list.");
        }
    }

    @Override
    public CommandResult execute() {
        if (indices == null) {
            Executor.terminatePrompt();
            return new CommandResult(MESSAGE_INVALID_DELETE_INDICES);
        } else {
            Executor.preparePromptConfirmation();
            Executor.setIndices(indices);

            try {
                return executePromptConfirmation();
            } catch (IndexOutOfBoundsException e) {
                Executor.terminatePrompt();
                return new CommandResult(MESSAGE_LIST_NUMBER_NOT_FOUND);
            }
        }
    }
}
