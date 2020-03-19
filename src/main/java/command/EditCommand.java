package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;
import tasks.Assignment;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditCommand extends Command {

    public static final String EDIT_COMMAND_WORD = "edit";
    public static final Pattern ASSIGNMENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<taskType>[^/]+)"
                    + "\\s+n/\\s*(?<assignmentName>[^/]+)"
                    + "\\s+m/\\s*(?<moduleName>[^/]+)"
                    + "\\s+d/\\s*(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
                    + "\\s+c/\\s*(?<comments>.+)$"
    );

    protected int editIndex;

    public EditCommand(int editIndex) {
        this.editIndex = editIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.NO_TASKS_MSG);
        }
        try {
            ui.showToUser(Messages.EDIT_PROMPT);
            ui.showToUser(Messages.DIVIDER);
            String input = ui.getUserInput();
            Task editedAssignment = editAssignment(input, ui);
            taskList.editTask(editIndex, editedAssignment);
            return new CommandResult(String.format(Messages.EDIT_SUCCESS_MESSAGE, editedAssignment));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR,
                    getRangeOfValidIndex(taskList)));
        }

    }

    public Assignment editAssignment(String userInput, Ui ui) {
        final Matcher matcher = ASSIGNMENT_PARAMETERS_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            ui.showToUser(Messages.ASSIGN_INCORRECT_FORMAT_ERROR);
        }

        LocalDateTime dateTime = null;
        try {
            dateTime = Parser.parseDate(matcher.group("dateTime"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            ui.showToUser(Messages.DATE_INCORRECT_OR_INVALID_ERROR);
        }

        String assignmentName = Parser.capitalize(matcher.group("assignmentName"));
        String moduleName = matcher.group("moduleName");
        String comments = Parser.capitalize(matcher.group("comments"));

        return new Assignment(assignmentName, moduleName, dateTime, comments);
    }



}
