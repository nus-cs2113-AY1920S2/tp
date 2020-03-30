package seedu.nuke.command.filtercommand;

import seedu.nuke.command.Command;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.*;
import seedu.nuke.directory.Module;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static seedu.nuke.parser.Parser.*;

public abstract class FilterCommand extends Command {
    public static final Pattern MODULE_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern CATEGORY_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern TASK_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern FILE_REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            + "(?<moduleCode>(?:\\s+" + MODULE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<categoryName>(?:\\s+" + CATEGORY_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<taskDescription>(?:\\s+" + TASK_PREFIX + "(?:\\s+\\w\\S*)+)?)"
            + "(?<optional>(?:\\s+-[ea])*)"
            + "(?<invalid>.*)"
    );
    public static final Pattern REGEX_OPTIONAL_FORMAT = Pattern.compile(
            "(?<exact>(?:\\s+" + EXACT_FLAG + ")?)"
            + "(?<all>(?:\\s+" + ALL_FLAG + ")?)"
    );

    protected ArrayList<Directory> createFilteredModuleList(String moduleKeyword, boolean isExact) {
        return filterModules(moduleKeyword, isExact);
    }

    protected ArrayList<Directory> createFilteredCategoryList(String moduleKeyword, String categoryKeyword,
                                                              boolean isExact, boolean isAll) {
        if (isAll) {
            return filterCategories(moduleKeyword, categoryKeyword, isExact);
        }
        // Fill up missing keywords first
        try {
            if (moduleKeyword.isEmpty()) {
                moduleKeyword = DirectoryTraverser.getBaseModule().getModuleCode();
            }
            if (categoryKeyword.isEmpty()) {
                categoryKeyword = DirectoryTraverser.getBaseCategory().getCategoryName();
            }
            return filterCategories(moduleKeyword, categoryKeyword, isExact);
        } catch (IncorrectDirectoryLevelException e) {
            return filterCategories(moduleKeyword, categoryKeyword, isExact);
        }
    }

    protected ArrayList<Directory> createFilteredTaskList(String moduleKeyword, String categoryKeyword,
          String taskKeyword, boolean isExact, boolean isAll) {
        if (isAll) {
            return filterTasks(moduleKeyword, categoryKeyword, taskKeyword, isExact);
        }
        // Fill up missing keywords first
        try {
            if (moduleKeyword.isEmpty()) {
                moduleKeyword = DirectoryTraverser.getBaseModule().getModuleCode();
            }
            if (categoryKeyword.isEmpty()) {
                categoryKeyword = DirectoryTraverser.getBaseCategory().getCategoryName();
            }
            if (taskKeyword.isEmpty()) {
                taskKeyword = DirectoryTraverser.getBaseTask().getDescription();
            }
            return filterTasks(moduleKeyword, categoryKeyword, taskKeyword, isExact);
        } catch (IncorrectDirectoryLevelException e) {
            return filterTasks(moduleKeyword, categoryKeyword, taskKeyword, isExact);
        }
    }

    protected ArrayList<Directory> createFilteredFileList(String moduleKeyword, String categoryKeyword,
            String taskKeyword, String fileKeyword, boolean isExact, boolean isAll) {
        if (isAll) {
            return filterFiles(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword, isExact);
        }
        // Fill up missing keywords first
        try {
            if (moduleKeyword.isEmpty()) {
                moduleKeyword = DirectoryTraverser.getBaseModule().getModuleCode();
            }
            if (categoryKeyword.isEmpty()) {
                categoryKeyword = DirectoryTraverser.getBaseCategory().getCategoryName();
            }
            if (taskKeyword.isEmpty()) {
                taskKeyword = DirectoryTraverser.getBaseTask().getDescription();
            }
            if (fileKeyword.isEmpty()) {
                fileKeyword = DirectoryTraverser.getBaseFile().getFileName();
            }
            return filterFiles(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword, isExact);
        } catch (IncorrectDirectoryLevelException e) {
            return filterFiles(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword, isExact);
        }
    }

    private ArrayList<Directory> filterModules(String moduleKeyword, boolean isExact) {
        ArrayList<Module> filteredModuleList =
                isExact ? ModuleManager.filterExact(moduleKeyword) : ModuleManager.filter(moduleKeyword);
        return new ArrayList<>(filteredModuleList);
    }

    private ArrayList<Directory> filterCategories(String moduleKeyword, String categoryKeyword, boolean isExact) {
        ArrayList<Category> filteredCategoryList =
                isExact ? ModuleManager.filterExact(moduleKeyword, categoryKeyword) :
                        ModuleManager.filter(moduleKeyword, categoryKeyword);
        return new ArrayList<>(filteredCategoryList);
    }

    private ArrayList<Directory> filterTasks(String moduleKeyword, String categoryKeyword, String taskKeyword,
                                             boolean isExact) {
        ArrayList<Task> filteredTaskList =
                isExact ? ModuleManager.filterExact(moduleKeyword, categoryKeyword, taskKeyword) :
                        ModuleManager.filter(moduleKeyword, categoryKeyword, taskKeyword);
        return new ArrayList<>(filteredTaskList);
    }

    private ArrayList<Directory> filterFiles(String moduleKeyword, String categoryKeyword, String taskKeyword,
             String fileKeyword, boolean isExact) {
        ArrayList<TaskFile> filteredTaskList =
                isExact ? ModuleManager.filterExact(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword) :
                        ModuleManager.filter(moduleKeyword, categoryKeyword, taskKeyword, fileKeyword);
        return new ArrayList<>(filteredTaskList);
    }
}
