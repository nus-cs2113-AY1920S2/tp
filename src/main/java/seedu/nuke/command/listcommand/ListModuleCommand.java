package seedu.nuke.command.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Module;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListModuleCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final Pattern REGEX_FORMAT = Pattern.compile(
      "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)+)" +
      "(?<optional>(?:\\s+-[ea])*)" +
      "(?<invalid>(?:\\s+-.*)*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)" +
            "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
    );

    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+)?)"),
            Pattern.compile("(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"),
            // Pattern.compile("(?<all>(?:" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:\\s+-(?:[^ae].*|[ae]\\S+)))")
    };

    private String moduleKeyWord;
    private boolean isExact;

    public ListModuleCommand(String moduleKeyWord, boolean isExact) {
        this.moduleKeyWord = moduleKeyWord;
        this.isExact = isExact;
    }

    protected ArrayList<Directory> createFilteredList() {
        ArrayList<Module> filteredModuleList =
                isExact ? ModuleManager.filterExact(moduleKeyWord) : ModuleManager.filter(moduleKeyWord);
        return new ArrayList<>(filteredModuleList);
    }

    @Override
    public CommandResult execute() {
        ArrayList<Directory> filteredModuleList = createFilteredList();
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.MODULE, filteredModuleList);
    }
}
