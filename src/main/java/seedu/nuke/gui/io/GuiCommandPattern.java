package seedu.nuke.gui.io;

import seedu.nuke.parser.Parser;

import java.util.regex.Pattern;

import static seedu.nuke.gui.io.GuiParser.ALL_FLAG;
import static seedu.nuke.gui.io.GuiParser.CATEGORY_PREFIX;
import static seedu.nuke.gui.io.GuiParser.DEADLINE_PREFIX;
import static seedu.nuke.gui.io.GuiParser.EXACT_FLAG;
import static seedu.nuke.gui.io.GuiParser.FILE_PREFIX;
import static seedu.nuke.gui.io.GuiParser.MODULE_PREFIX;
import static seedu.nuke.gui.io.GuiParser.PRIORITY_PREFIX;
import static seedu.nuke.gui.io.GuiParser.TASK_PREFIX;

public class GuiCommandPattern {
    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\s*\\S+)(?<parameters>.*)");

    public static final Pattern ADD_MODULE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<invalid>.*)");

    public static final Pattern ADD_CATEGORY_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern ADD_TASK_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<prioritySecond>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern ADD_FILE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<fileInfo>(?:\\s+" + FILE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern DELETE_AND_LIST_MODULE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<allSecond>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>.*)");

    public static final Pattern DELETE_AND_LIST_CATEGORY_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<allSecond>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>.*)");

    public static final Pattern DELETE_AND_LIST_TASK_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<allSecond>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>.*)");

    public static final Pattern DELETE_AND_LIST_FILE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<allSecond>(?:\\s+" + ALL_FLAG + ")?)"
            + "(?<invalid>.*)");

    public static final Pattern LIST_TASK_SORTED_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<priority>(?:\\s+" + Parser.PRIORITY_PREFIX + ")?)"
            + "(?<deadline>(?:\\s+" + Parser.DEADLINE_PREFIX + ")?)"
            + "(?<prioritySecond>(?:\\s+" + Parser.PRIORITY_PREFIX + ")?)"
            + "(?<invalid>.*)");

    public static final Pattern DUE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<all>(?:\\s+" + Parser.ALL_FLAG + ")?)"
            + "(?<invalid>.*)"
    );

    public static final Pattern EDIT_MODULE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern EDIT_CATEGORY_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern EDIT_TASK_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<priority>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<prioritySecond>(?:\\s+" + PRIORITY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern EDIT_FILE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<fileInfo>(?:\\s+" + FILE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern DONE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");

    public static final Pattern OPEN_FILE_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<invalid>.*)");
}
