package seedu.command;

import seedu.common.Messages;
import seedu.exception.CommandExceptions.FindEmptyPatternException;
import seedu.tasks.Task;
import static seedu.common.Constants.TAB;
import java.util.ArrayList;


public class FindCommand extends Command {

    private String pattern;

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_INFO = COMMAND_WORD + ": finds tasks with given pattern";
    public static final String COMMAND_USAGE = COMMAND_WORD + " [PATTERN]";

    public FindCommand(String pattern) {
        this.pattern = pattern.trim().substring(4).trim();
    }

    @Override
    public CommandResult execute() {

        String feedback = "";

        try {
            if (pattern.isEmpty()) {
                throw new FindEmptyPatternException();
            }

            ArrayList<Task> tasks = taskList.findTasks(pattern);

            if (tasks.isEmpty()) {
                feedback += TAB + String.format(Messages.MESSAGE_NO_FOUND, pattern);

            } else {
                feedback = formatFeedback(tasks);

            }

        } catch (FindEmptyPatternException e) {
            feedback = TAB + Messages.MESSAGE_EMPTY_PATTERN;

        } finally {
            return new CommandResult(feedback);
        }
    }

    private String formatFeedback(ArrayList<Task> tasks) {
        String list = "";
        String feedback = TAB;

        for (int i = 0; i < tasks.size(); i++) {
            list += TAB + TAB + (i + 1) + ". " + tasks.get(i) + "\n";
        }

        feedback += String.format(Messages.MESSAGE_SUCCESS, list);

        return feedback;
    }

}
