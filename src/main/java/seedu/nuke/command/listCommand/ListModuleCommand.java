package seedu.nuke.command.listCommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.command.listCommand.ListCommand;
import seedu.nuke.module.Module;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.ALL_FLAG;
import static seedu.nuke.parser.Parser.EXACT_FLAG;
import static seedu.nuke.util.Message.MESSAGE_SHOW_MODULES;

public class ListModuleCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsm";
    public static final String MESSAGE_USAGE = COMMAND_WORD;
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("(?<identifier>^\\s*([^-]+)?)"),
            Pattern.compile("(?<all>(?:" + ALL_FLAG + ")?)"),
            Pattern.compile("(?<exact>(?:" + EXACT_FLAG + ")?)"),
            Pattern.compile("(?<invalid>(?:-(?:[^ae].*|[ae]\\S+)))")
    };

    @Override
    public CommandResult execute() {
        ArrayList<String> modules = new ArrayList<>();
        for (Module module: moduleManager.getModuleList()) {
            // modules.add(module.getModuleCode() + " " + module.getTitle() + " " + module.getDescription());
            modules.add(module.getModuleCode() + " " + module.getTitle());
        }
        return new CommandResult(MESSAGE_SHOW_MODULES, true, modules);
    }
}
