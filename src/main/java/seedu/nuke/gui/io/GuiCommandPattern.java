package seedu.nuke.gui.io;

import java.util.regex.Pattern;

import static seedu.nuke.gui.io.GuiParser.ALL_FLAG;
import static seedu.nuke.gui.io.GuiParser.CATEGORY_NAME_PREFIX;
import static seedu.nuke.gui.io.GuiParser.DEADLINE_PREFIX;
import static seedu.nuke.gui.io.GuiParser.EXACT_FLAG;
import static seedu.nuke.gui.io.GuiParser.MODULE_CODE_PREFIX;
import static seedu.nuke.gui.io.GuiParser.PRIORITY_PREFIX;
import static seedu.nuke.gui.io.GuiParser.TASK_DESCRIPTION_PREFIX;

public class GuiCommandPattern {
    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\s*\\w+)(?<parameters>.*)");

    public static final Pattern ADD_MODULE_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)");

    public static final Pattern ADD_CATEGORY_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*?)");

    public static final Pattern ADD_TASK_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*?)");

    public static final Pattern DELETE_AND_LIST_MODULE_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)");

    public static final Pattern DELETE_AND_LIST_CATEGORY_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)(?:\\s*)");


    public static final Pattern DELETE_AND_LIST_TASK_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)(?:\\s*)");


    public static final Pattern EDIT_MODULE_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)");

    public static final Pattern EDIT_CATEGORY_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)");

    public static final Pattern EDIT_TASK_FORMAT = Pattern.compile(
            "(?<identifier>(?:(?:\\s+[^-\\s]\\S*)+|^[^-\\s]\\S*)?)"
            + "(?<moduleCode>(?:\\s+" + MODULE_CODE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_NAME_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_DESCRIPTION_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+[^-\\s]\\S*)+)?)"
            + "(?<invalid>(?:\\s+-.*)*)(?:\\s*)");
}
