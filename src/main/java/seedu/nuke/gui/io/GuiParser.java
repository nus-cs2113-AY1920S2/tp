package seedu.nuke.gui.io;
import javafx.scene.paint.Color;
import seedu.nuke.command.ChangeDirectoryCommand;
import seedu.nuke.command.addcommand.AddCategoryCommand;
import seedu.nuke.command.addcommand.AddModuleCommand;
import seedu.nuke.command.addcommand.AddTaskCommand;
import seedu.nuke.command.editcommand.EditCategoryCommand;
import seedu.nuke.command.editcommand.EditModuleCommand;
import seedu.nuke.command.editcommand.EditTaskCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListCategoryCommand;
import seedu.nuke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiParser {
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\s*\\w+)(?<parameters>.*)");
    private static final String WHITESPACES = "\\s+";
    private static final String PARAMETER_SPLITTER = " ";
    private static final int COMMAND_PARAMETER_MAXIMUM_LIMIT = 2;
    private static final int COMMAND_WORD_INDEX = 0;
    private static final int PARAMETER_WORD_INDEX = 1;

    public static final String MODULE_CODE_PREFIX = "-m";
    public static final String CATEGORY_NAME_PREFIX = "-c";
    public static final String TASK_DESCRIPTION_PREFIX = "-t";
    public static final String PRIORITY_PREFIX = "-p";
    public static final String DEADLINE_PREFIX = "-d";
    public static final String ALL_FLAG = "-a";
    public static final String EXACT_FLAG = "-e";

    private static final String[] COMMAND_WORDS = {
            AddModuleCommand.COMMAND_WORD, AddCategoryCommand.COMMAND_WORD, AddTaskCommand.COMMAND_WORD,
            DeleteModuleCommand.COMMAND_WORD, DeleteCategoryCommand.COMMAND_WORD, DeleteTaskCommand.COMMAND_WORD,
            ListModuleCommand.COMMAND_WORD, ListCategoryCommand.COMMAND_WORD, ListTaskCommand.COMMAND_WORD,
            EditModuleCommand.COMMAND_WORD, EditCategoryCommand.COMMAND_WORD, EditTaskCommand.COMMAND_WORD,
            ChangeDirectoryCommand.COMMAND_WORD
    };


    /**
     * Parses the command word in the input string from the GUI console.
     *
     * @param input
     *  The user input read by the <b>GUI</b>
     */
    public Color parseCommandWord(String input) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return Color.CRIMSON;
        }
        String commandWord = matcher.group("commandWord").toLowerCase();
        // String parameters = matcher.group("parameters");

        if (!isPartOfCommandWord(commandWord)) {
            return Color.CRIMSON;
        }

        if (isMatchingCommandWord(commandWord)) {
            return Color.GREEN;
        } else {
            return Color.DARKGRAY;
        }

    }

    private boolean isPartOfCommandWord(String givenCommandWord) {
        for (String commandWord : COMMAND_WORDS) {
            if (commandWord.contains(givenCommandWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMatchingCommandWord(String givenCommandWord) {
        for (String commandWord : COMMAND_WORDS) {
            if (commandWord.equals(givenCommandWord)) {
                return true;
            }
        }
        return false;
    }
}
