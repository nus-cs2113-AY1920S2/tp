package seedu.nuke.command.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Module;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.EXACT_FLAG;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListModuleCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
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

    @Override
    public CommandResult execute() {
        ArrayList<Module> moduleList =
                isExact ? ModuleManager.filterExact(moduleKeyWord) : ModuleManager.filter(moduleKeyWord);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.MODULE, new ArrayList<>(moduleList));
    }
}
